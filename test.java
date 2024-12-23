public void b(com.immomo.momo.service.bean.w r4) {
    com.immomo.momo.messages.b.b r0 = com.immomo.momo.messages.memcache.ChatMsgMemCache.f57644b;
    String r1 = r4.e;
    com.immomo.momo.service.bean.Message r0 = r0.b(r1);

    if (r0 != null) {
        r4.a(r0);
        return;
    }

    String r0 = r4.b();
    if (TextUtils.isEmpty(r0)) {
        return;
    }

    String r1 = "ChatMsgDB";
    String r2 = " 从DB初始化最新消息";
    MDLog.d(r1, r2);

    int r1 = r4.f54086c;
    if (r1 == 0) {
        return;
    }

    switch (r1) {
        case 1:
            com.immomo.momo.messages.service.h r1 = com.immomo.momo.messages.service.h.a();
            String r2 = r4.f;
            r0 = r1.a(r2, r0);
            r4.a(r0);
            break;
        case 2:
            com.immomo.momo.messages.service.q r1 = com.immomo.momo.messages.service.q.a();
            String r2 = r4.f;
            r0 = r1.a(r2, r0);
            r4.a(r0);
            break;
        case 6:
            com.immomo.momo.messages.service.d r1 = com.immomo.momo.messages.service.d.a();
            String r2 = r4.f;
            r0 = r1.a(r2, r0);
            r4.a(r0);
            break;
        case 7:
            com.immomo.momo.messages.service.l r1 = com.immomo.momo.messages.service.l.a();
            String r2 = r4.f;
            r0 = r1.c(r2, r0);
            r4.a(r0);
            break;
        case 9:
            com.immomo.momo.messages.service.l r1 = com.immomo.momo.messages.service.l.a();
            r0 = r1.j(r0);
            r4.a(r0);
            break;
        case 15:
            return;
        case 17:
            return;
        case 22:
            com.immomo.momo.messages.service.j r1 = com.immomo.momo.messages.service.j.a();
            String r2 = r4.f;
            r0 = r1.b(r2, r0);
            r4.a(r0);
            break;
        case 26:
            com.immomo.momo.messages.service.n r1 = com.immomo.momo.messages.service.n.a();
            r0 = r1.f(r0);
            r4.a(r0);
            break;
        default:
            return;
    }

    com.immomo.momo.service.bean.Message r0 = r4.a();
    if (r0 != null) {
        com.immomo.momo.service.sessions.e.a(r0);
    }
}
