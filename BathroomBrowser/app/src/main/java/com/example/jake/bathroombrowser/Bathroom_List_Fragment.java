package com.example.jake.bathroombrowser;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Bathroom_List_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Bathroom_List_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Bathroom_List_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Bathroom_List_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Bathroom_List_Fragment newInstance(String param1, String param2) {
        Bathroom_List_Fragment fragment = new Bathroom_List_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.bathroom_list_fragment, container, false);

        //set bathroom description, mParam1 will be bathroom description drawn from database
        // *Name, floor, building, other info
        TextView descriptor = (TextView)view.findViewById(R.id.description);
        descriptor.setText(mParam1);

        //set bathroom distance from user
        //calculate elsewhere with gps coords and pass to fragment constructor as param2, as a string
        TextView distance = (TextView)view.findViewById(R.id.distance);
        distance.setText(mParam2);

        //add onclick to open bathroom screen
        View image = view.findViewById(R.id.thumbnail);
        image.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getContext(),BathroomActivity.class));
                    }
                }
        );
        return view;
    }



}
