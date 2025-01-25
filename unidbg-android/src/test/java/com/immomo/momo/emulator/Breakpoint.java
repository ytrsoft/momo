package com.immomo.momo.emulator;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Module;
import com.github.unidbg.arm.context.RegisterContext;
import com.github.unidbg.debugger.Debugger;


public class Breakpoint {

    private final AndroidEmulator emulator;
    private final Module module;
    private String symbolName;
    private BreakpointCallback breakpointCallback;

    public interface BreakpointCallback {
        void onBreakpoint(Debugger debugger, RegisterContext context);
    }

    public Breakpoint(AndroidEmulator emulator, Module module) {
        this.emulator = emulator;
        this.module = module;
    }

    private void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }

    public void setBreakpointCallback(BreakpointCallback callback) {
        this.breakpointCallback = callback;
    }

    private void debugger(Debugger debugger, long address, BreakpointCallback callback) {
        debugger.addBreakPoint(address, (em, addr) -> {
            Debugger next = em.attach();
            RegisterContext context = em.getContext();
            callback.onBreakpoint(next, context);
            return true;
        });
    }

    private void addBreakpoint() {
        Debugger debugger = emulator.attach();
        long address = module.findSymbolByName(symbolName).getAddress();
        debugger(debugger, address, breakpointCallback);
    }

    public void nextBreakpoint(Debugger debugger, long address, BreakpointCallback callback) {
        debugger(debugger, address, callback);
    }

    public void addBreakpoint(String symbolName) {
        setSymbolName(symbolName);
        addBreakpoint();
    }
}
