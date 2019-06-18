package jm.org.bysj.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import jm.org.bysj.Entity.AlbumEntity;
import jm.org.bysj.R;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder>{
    private Context mContext;
    private List<AlbumEntity> albumEntityList;
    private AlbumAdapterOnclick albumAdapterOnclick;

    public AlbumAdapter(Context context,List<AlbumEntity> list,AlbumAdapterOnclick onclick){
        this.mContext=context;
        this.albumEntityList=list;
        this.albumAdapterOnclick=onclick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.item_album,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(mContext).load(albumEntityList.get(position).getName()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (albumAdapterOnclick!=null){
                    albumAdapterOnclick.onClick(albumEntityList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return albumEntityList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.iv_item_image);
        }
    }
    public interface AlbumAdapterOnclick{
        void onClick(AlbumEntity entity);
    }
}
