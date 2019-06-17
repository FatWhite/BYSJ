package jm.org.bysj.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class LogsDetailsModels {
    @Id(autoincrement = true)
    private Long id;
    private String imageName;
    private String tagName;
    private String tagDetail;
    @Generated(hash = 1367780241)
    public LogsDetailsModels(Long id, String imageName, String tagName,
            String tagDetail) {
        this.id = id;
        this.imageName = imageName;
        this.tagName = tagName;
        this.tagDetail = tagDetail;
    }
    @Generated(hash = 978513371)
    public LogsDetailsModels() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTagName() {
        return this.tagName;
    }
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
    public String getTagDetail() {
        return this.tagDetail;
    }
    public void setTagDetail(String tagDetail) {
        this.tagDetail = tagDetail;
    }
    public String getImageName() {
        return this.imageName;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
