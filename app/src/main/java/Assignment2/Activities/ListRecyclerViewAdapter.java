package Assignment2.Activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Models.Contact;

/**
 * ListRecyclerViewAdapter, takes care of the individual recycler items
 */
public class ListRecyclerViewAdapter extends  RecyclerView.Adapter<ListRecyclerViewAdapter.MainListItemViewHolder> {
    private ArrayList<Contact> contactList;
    private OnContactListener _onContactListener;

    public ListRecyclerViewAdapter(ArrayList<Contact> contactList, OnContactListener onContactListener) {

        this.contactList = contactList;
        this._onContactListener = onContactListener;
    }

    @NonNull
    @Override
    public MainListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        MainListItemViewHolder vh = new MainListItemViewHolder(v, _onContactListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MainListItemViewHolder holder, int position) {
        holder.txtViewFirstName.setText(contactList.get(position).getFirstName());
        holder.txtViewLastName.setText(contactList.get(position).getLastName());
        holder.txtViewPhone.setText(contactList.get(position).getPhone());
        holder.txtViewDate.setText(contactList.get(position).getDateEntered());
        holder.txtViewId.setText(String.valueOf(contactList.get(position).getId()));
    }

    @Override
    public int getItemCount()
    {
        return contactList == null? 0 : contactList.size();
    }

    class MainListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        OnContactListener onContactListener;
        private TextView txtViewFirstName;
        private TextView txtViewLastName;
        private TextView txtViewDate;
        private TextView txtViewPhone;
        private TextView txtViewId;
        private Button  button;
        public MainListItemViewHolder(@NonNull View itemView, OnContactListener onContactListener) {
            super(itemView);
            txtViewFirstName = itemView.findViewById(R.id.txt_main_item_firstname);
            txtViewLastName= itemView.findViewById(R.id.text_lastname);
            txtViewPhone = itemView.findViewById(R.id.txt_main_item_phone);
            txtViewDate =itemView.findViewById(R.id.text_date);
            txtViewId =itemView.findViewById(R.id.txt_main_item_Id);
            button = itemView.findViewById(R.id.edit_button);
            this.onContactListener = onContactListener;
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onContactListener.onContactClick(getAdapterPosition());
        }
    }
    public interface OnContactListener {
        void onContactClick(int position);
    }
}