package jm.org.bysj.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 展示效果实体
 */
public class AlbumEntity {
    private String name;
    private List<ImageTextTagsEntity> imageTextTagsEntityList=new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ImageTextTagsEntity> getImageTextTagsEntityList() {
        return imageTextTagsEntityList;
    }

    public void setImageTextTagsEntityList(List<ImageTextTagsEntity> imageTextTagsEntityList) {
        this.imageTextTagsEntityList = imageTextTagsEntityList;
    }
}
