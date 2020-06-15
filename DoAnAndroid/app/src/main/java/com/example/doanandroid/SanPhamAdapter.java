package com.example.doanandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SanPhamAdapter extends BaseAdapter {

    private Context  context;
    private int layout;
    private List<SanPham> sanPhamList;
    Bitmap bitmap;

    public SanPhamAdapter(Context context, int layout, List<SanPham> sanPhamList) {
        this.context = context;
        this.layout = layout;
        this.sanPhamList = sanPhamList;
    }

    public SanPhamAdapter() {
    }

    @Override
    public int getCount() {
        return sanPhamList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public static class ViewHolder{
        TextView txtTenSP,txtMoTaSP,txtGia;
        ImageView imgHinhSP;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null){
            holder  = new ViewHolder();
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.txtTenSP =convertView.findViewById(R.id.textViewImageTrangChu);
            holder.txtMoTaSP = convertView.findViewById(R.id.textViewMoTaItemTrangChu);
            holder.txtGia = convertView.findViewById(R.id.textViewGiaTrangChu);
            holder.imgHinhSP = convertView.findViewById(R.id.imageViewItemTrangChu);

            convertView.setTag(holder);
        }
        else
        {
            holder= (ViewHolder) convertView.getTag();
        }

        SanPham sanPham =sanPhamList.get(position);
        holder.txtTenSP.setText(sanPham.getTENSANPHAM());
        holder.txtGia.setText(sanPham.getGIA()+"VNĐ");
        holder.txtMoTaSP.setText(sanPham.getMOTASANPHAM());
        Picasso.with(context)
                .load(sanPham.getHINHANH())
                .placeholder(R.drawable.ic_wait)
                .error(R.drawable.ic_offline)
                .into(holder.imgHinhSP);
        //new GetImageFromUrl(holder.imgHinhSP).execute(sanPham.getHINHANH());
        return convertView;
    }

    public class GetImageFromUrl extends AsyncTask<String, Void, Bitmap>{
        ImageView imageView;
        public GetImageFromUrl(ImageView img){
            this.imageView = img;
        }
        @Override
        protected Bitmap doInBackground(String... url) {
            String stringUrl = url[0];
            bitmap = null;
            InputStream inputStream;
            try {
                inputStream = new java.net.URL(stringUrl).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap){
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }
}
