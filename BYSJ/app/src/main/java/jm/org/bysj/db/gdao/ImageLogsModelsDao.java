package jm.org.bysj.db.gdao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import jm.org.bysj.db.ImageLogsModels;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "IMAGE_LOGS_MODELS".
*/
public class ImageLogsModelsDao extends AbstractDao<ImageLogsModels, Long> {

    public static final String TABLENAME = "IMAGE_LOGS_MODELS";

    /**
     * Properties of entity ImageLogsModels.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property LogsJson = new Property(2, String.class, "logsJson", false, "LOGS_JSON");
    }


    public ImageLogsModelsDao(DaoConfig config) {
        super(config);
    }
    
    public ImageLogsModelsDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"IMAGE_LOGS_MODELS\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"LOGS_JSON\" TEXT);"); // 2: logsJson
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_IMAGE_LOGS_MODELS_NAME ON \"IMAGE_LOGS_MODELS\"" +
                " (\"NAME\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"IMAGE_LOGS_MODELS\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ImageLogsModels entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String logsJson = entity.getLogsJson();
        if (logsJson != null) {
            stmt.bindString(3, logsJson);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ImageLogsModels entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String logsJson = entity.getLogsJson();
        if (logsJson != null) {
            stmt.bindString(3, logsJson);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ImageLogsModels readEntity(Cursor cursor, int offset) {
        ImageLogsModels entity = new ImageLogsModels( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // logsJson
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ImageLogsModels entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setLogsJson(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ImageLogsModels entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ImageLogsModels entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ImageLogsModels entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
