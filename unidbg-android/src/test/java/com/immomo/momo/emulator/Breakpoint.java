package com.immomo.momo.emulator;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Emulator;
import com.github.unidbg.Module;
import com.github.unidbg.arm.context.RegisterContext;
import com.github.unidbg.debugger.BreakPointCallback;
import com.github.unidbg.debugger.Debugger;


public class Breakpoint {

    private final AndroidEmulator emulator;
    private final Module module;
    private String symbolName;
    private Hook hook;
    private Leave leave;

    public interface Hook {
        void onHook(Debugger debugger, RegisterContext context);
    }

    public interface Leave {
        void onLeave(RegisterContext context);
    }

    public Breakpoint(AndroidEmulator emulator, Module module) {
        this.emulator = emulator;
        this.module = module;
    }

    private void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }

    public void setOnHook(Hook hook) {
        this.hook = hook;
    }

    public void setOnLeave(Leave leave) {
        this.leave = leave;
    }

    private void setBreakpoint() {
        Debugger debugger = emulator.attach();
        long address = module.findSymbolByName(symbolName).getAddress();
        debugger.addBreakPoint(address, (em, addr) -> {
            Debugger next = em.attach();
            RegisterContext context = em.getContext();
            if (hook != null) {
                hook.onHook(next, context);
            }
            return true;
        });
    }

    public void next(Debugger debugger, RegisterContext context) {
        long peer = context.getLRPointer().peer;
        debugger.addBreakPoint(peer, (emulator, address) -> {
            if (leave != null) {
                leave.onLeave(context);
            }
            return true;
        });
    }

    public void register(String symbolName) {
        setSymbolName(symbolName);
        setBreakpoint();
    }
}
