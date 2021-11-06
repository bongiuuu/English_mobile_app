package tdtu.final_mobile.home.notification;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import tdtu.final_mobile.R;

public class NotificationViewHolder extends RecyclerView.ViewHolder {
    TextView tvNotificationTitle;
    CardView cvNotification;


    public NotificationViewHolder(@NonNull View itemView) {
        super(itemView);
        this.tvNotificationTitle = itemView.findViewById(R.id.tvNotificationTitle);
        this.cvNotification = itemView.findViewById(R.id.cvNotification);
    }
}
