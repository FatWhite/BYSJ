package jm.org.bysj.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import jm.org.bysj.db.gdao.DaoMaster;
import jm.org.bysj.db.gdao.DaoSession;
import jm.org.bysj.db.gdao.LogsDetailsModelsDao;

public class DbSession {
    private static DbSession dbSession=new DbSession();
    private DbSession(){

    }
    public static DbSession getInstance(){
        return dbSession;
    }

    private static DaoMaster.DevOpenHelper mHelper;
    private static SQLiteDatabase db;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    public void initDb(Context context){
        mHelper = new DaoMaster.DevOpenHelper(context, "ImageTextLogs", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    /**
     * 插入一个图文日记
     * @param models
     */
    public static void insertImageTextLogs(ImageLogsModels models){
        mDaoSession.getImageLogsModelsDao().insertOrReplace(models);
    }

    /**
     * 查询图文日记列表
     * @return
     */
    public static List<ImageLogsModels> queryImageLogs(){
        return mDaoSession.getImageLogsModelsDao().queryBuilder().list();
    }

    /**
     * 插入一个图文日记中的一个log详情
     * @param models
     */
    public static void insertImageTextDetail(LogsDetailsModels models){
        mDaoSession.getLogsDetailsModelsDao().insertOrReplace(models);
    }

    /**
     * 根据tag name 查询log详情
     * @param name
     * @return
     */
    public static LogsDetailsModels queryLogsDetail(String name){
        return mDaoSession.getLogsDetailsModelsDao().queryBuilder().where(LogsDetailsModelsDao.Properties.TagName.eq(name)).unique();
    }

    /**
     * 根据图片的名字查询对应的所有log
     * @param name
     * @return
     */
    public static List<LogsDetailsModels> getLogsListByImageName(String name){
        List<LogsDetailsModels> classifyEntities;
        classifyEntities=mDaoSession.getLogsDetailsModelsDao().queryBuilder()
                .where(LogsDetailsModelsDao.Properties.ImageName.eq(name)).list();
        return classifyEntities;
    }
}
