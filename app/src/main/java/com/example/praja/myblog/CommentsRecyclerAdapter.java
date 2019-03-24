package com.example.praja.myblog;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;
import java.util.List;

public class CommentsRecyclerAdapter extends RecyclerView.Adapter<CommentsRecyclerAdapter.ViewHolder> {

    public List<Comments> commentsList;
    public Context context;
    String commentId;
    private List<User> user_list;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    public CommentsRecyclerAdapter(List<Comments> commentsList,String commentId){

        this.commentsList = commentsList;
        this.commentId=commentId;

    }

    @NonNull
    @Override
    public CommentsRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comment_list_item, viewGroup, false);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        context =viewGroup.getContext();
        return new CommentsRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CommentsRecyclerAdapter.ViewHolder viewHolder, int i) {
        viewHolder.setIsRecyclable(false);
        final String blogCommentId = commentsList.get(i).BlogCommentId;
                String commentMessage = commentsList.get(i).getMessage();
        viewHolder.setComment_message(commentMessage);

        try {
            long millisecond = commentsList.get(i).getTimestamp().getTime();
            android.text.format.DateFormat df = new android.text.format.DateFormat();
            String dateString = df.format("MM/dd/yyyy HH:mm:ss", new Date(millisecond)).toString();
            viewHolder.setTime(dateString);
        } catch (Exception e) {

           // Toast.makeText(context, "Exception : " + e.getMessage(), Toast.LENGTH_SHORT).show();

        }
        firebaseFirestore.collection("Posts/" + commentId + "/Comments").addSnapshotListener((Activity) context,new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (!documentSnapshots.isEmpty()) {

                    int count = documentSnapshots.size();
                    viewHolder.updateCommentsCount(count);


                } else {
                  //   viewHolder.updateCommentsCount(0);
                }
            }

        });

    }

    @Override
    public int getItemCount() {
        if(commentsList != null) {

            return commentsList.size();

        }  else {

            return 0;

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View mView;
private TextView blogCommentsCount;
private  TextView comment_date;
        private TextView comment_message;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView= itemView;
        }
        public void setComment_message(String message){


            comment_message = mView.findViewById(R.id.comment_message);
            comment_message.setText(message);

        }
        public void updateCommentsCount(int count){

            blogCommentsCount = mView.findViewById(R.id.blog_comment_count);
            blogCommentsCount.setText(count+"Comments");

        }
        public void setTime(String date){
            comment_date = mView.findViewById(R.id.comment_date);
            comment_date.setText(date);
        }
    }
}
