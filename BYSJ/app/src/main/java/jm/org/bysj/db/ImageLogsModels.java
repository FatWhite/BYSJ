package jm.org.bysj.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;

@Entity
public class ImageLogsModels {
    @Id(autoincrement = true)
    private Long id;
    @Index(unique = true)
    private String name;
    private String logsJson;
    @Generated(hash = 1765940229)
    public ImageLogsModels(Long id, String name, String logsJson) {
        this.id = id;
        this.name = name;
        this.logsJson = logsJson;
    }
    @Generated(hash = 295771543)
    public ImageLogsModels() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLogsJson() {
        return this.logsJson;
    }
    public void setLogsJson(String logsJson) {
        this.logsJson = logsJson;
    }
}
