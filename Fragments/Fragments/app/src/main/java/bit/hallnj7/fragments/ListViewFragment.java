package bit.hallnj7.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ListViewFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View fragmentView = inflater.inflate(R.layout.fragment_list_view, container, false);
        ListView lvAnimals = (ListView) fragmentView.findViewById(R.id.listViewAnimals);
        String[] groups = {"Cat", "Dog", "Horse", "Goat", "Rabbit"};
        ArrayAdapter<String> animalAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, groups);
        lvAnimals.setAdapter(animalAdapter);
        return fragmentView;
    }
}
