package jm.org.bysj;

import android.app.Application;

import jm.org.bysj.db.DbSession;

public class IApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DbSession.getInstance().initDb(this);
    }
}
