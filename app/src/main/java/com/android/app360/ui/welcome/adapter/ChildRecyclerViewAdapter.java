package com.android.app360.ui.welcome.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.app360.R;
import com.android.app360.ui.welcome.WelcomeActivity;
import com.android.appcompose.network.model.Classroom;
import com.android.appcompose.network.model.Mentor;

import java.util.ArrayList;

public class ChildRecyclerViewAdapter extends RecyclerView.Adapter<ChildRecyclerViewAdapter.ViewHolder>  {

    //Member variables
    private ArrayList<Classroom> classroomData = new ArrayList<Classroom>();
    private ArrayList<Mentor> mentorData  = new ArrayList<Mentor>();
    private Context mContext;
    private String dataType;
    /**
     * Constructor that passes in the sports data and the context
     * @param sportsData ArrayList containing the sports data
     * @param context Context of the application
     */
    public ChildRecyclerViewAdapter(Context context, ArrayList<Classroom> classrooms, String type) {

        this.mContext = context;
        this.dataType = type;
        classroomData = classrooms;

    }

    public ChildRecyclerViewAdapter(Context context, ArrayList<Mentor> mentors) {

        this.mContext = context;
        this.dataType = "Featured Members";

        mentorData = mentors;
    }




    /**
     * Required method for creating the viewholder objects.
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly create ViewHolder.
     */
    @Override
    public ChildRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChildRecyclerViewAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.welcome_grid_item, parent, false));
    }

    /**
     * Required method that binds the data to the viewholder.
     * @param holder The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(ChildRecyclerViewAdapter.ViewHolder holder, int position) {
        switch (this.dataType){
            case "Featured Classrooms":
                //Get current sport
                Classroom currentSport = classroomData.get(position);
                //Populate the textviews with data
                holder.bindToClassroom(currentSport);
                break;
            case "Featured Members":
                //Get current sport
                Mentor mentor = mentorData.get(position);
                //Populate the textviews with data
                holder.bindToMentor(mentor);
                break;
            default:
                Log.d("","NA");
        }

    }


    /**
     * Required method for determining the size of the data set.
     * @return Size of the data set.
     */
    @Override
    public int getItemCount() {
        int totalItems = 0;
        switch (this.dataType){
            case "Featured Classrooms":
                //Get current sport
                totalItems = classroomData.size();
                break;
            case "Featured Members":
                //Get current sport
                totalItems = mentorData.size();
                break;
            default:
                Log.d("","NA");
        }
        return totalItems;
    }

    public ArrayList<Classroom> getClassroomData() {
        return classroomData;
    }

    public void setClassroomData(ArrayList<Classroom> classroomData) {
        this.classroomData = classroomData;
    }

    public ArrayList<Mentor> getMentorData() {
        return mentorData;
    }

    public void setMentorData(ArrayList<Mentor> mentorData) {

        this.mentorData = mentorData;
    }


    /**
     * ViewHolder class that represents each row of data in the RecyclerView
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        //Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mInfoText;
        private TextView mNewsText;
        private Button mBtnAction1;
        private Button mBtnAction2;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         * @param itemView The rootview of the list_item.xml layout file
         */
        ViewHolder(View itemView) {
            super(itemView);

            //Initialize the views
            mTitleText = (TextView)itemView.findViewById(R.id.title);

        }

        void bindToClassroom(Classroom item){
            //Populate the textviews with data
            mTitleText.setText(item.getChash());


        }
        void bindToMentor(Mentor item){
            //Populate the textviews with data
            mTitleText.setText(item.getUhash());


        }
    }
}