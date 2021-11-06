package tdtu.final_mobile.home.notification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tdtu.final_mobile.R;
import tdtu.final_mobile.presentation.quiz.OnItemClickAction;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationViewHolder> {
    final List<Notification> notifications;
    final Context context;
    final LayoutInflater layoutInflater;
    OnItemClickAction onClickAction;

    public NotificationAdapter(List<Notification> notifications, Context context) {
        this.notifications = notifications;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recyclerViewItem = layoutInflater.inflate(R.layout.custom_notification_layout, parent, false);

        recyclerViewItem.setOnClickListener(v -> handleRecyclerItemClick((RecyclerView)parent, v));
        return new NotificationViewHolder(recyclerViewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        holder.tvNotificationTitle.setText(notifications.get(position).getNotificationTitle());
        holder.cvNotification.setOnClickListener(v -> {
            onClickAction.onClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return this.notifications.size();
    }

    public void handleRecyclerItemClick(RecyclerView recyclerView, View itemView) {
        int itemPosition = recyclerView.getChildLayoutPosition(itemView);
        Notification notification = this.notifications.get(itemPosition);

        Toast.makeText(this.context, notification.getNotificationTitle(), Toast.LENGTH_SHORT).show();
    }
}
