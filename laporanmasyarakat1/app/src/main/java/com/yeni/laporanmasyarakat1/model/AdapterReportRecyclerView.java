package com.yeni.laporanmasyarakat1.model;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.yeni.laporanmasyarakat1.CreateActivity;
import com.yeni.laporanmasyarakat1.DetailActivity;
import com.yeni.laporanmasyarakat1.R;
import com.yeni.laporanmasyarakat1.ReadActivity;

import java.util.ArrayList;
public class AdapterReportRecyclerView extends
        RecyclerView.Adapter<AdapterReportRecyclerView.ViewHolder> {
    private ArrayList<Report> listReport;
    private Context context;
    private FirebaseDataListener listener;

    public AdapterReportRecyclerView(ArrayList<Report> reports, Context ctx) {
        listReport = reports;
        context = ctx;
        listener = (ReadActivity)ctx;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tv_listreport);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_report, parent,
                        false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String name = listReport.get(position).getTitle();
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(DetailActivity.getActIntent((Activity)
                        context).putExtra("data", listReport.get(position)));
            }
        });
        holder.tvTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_view);
                dialog.setTitle("Pilih Aksi");
                dialog.show();
                Button editButton = (Button) dialog.findViewById(R.id.bt_edit_data);
                Button delButton = (Button) dialog.findViewById(R.id.bt_delete_data);

                editButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override

                            public void onClick(View view) {

                                dialog.dismiss();
                                context.startActivity(CreateActivity.getActIntent((Activity)
                                        context).putExtra("data", listReport.get(position)));
                            }
                        }
                );

                delButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override

                            public void onClick(View view) {

                                dialog.dismiss();

                                listener.onDeleteData(listReport.get(position), position);
                            }
                        }
                );
                return true;
            }
        });
        holder.tvTitle.setText(name);
    }
    @Override
    public int getItemCount() {
        return listReport.size();

    }
    public interface FirebaseDataListener{
        void onDeleteData(Report report, int position);
    }
}
