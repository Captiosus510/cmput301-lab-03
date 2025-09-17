package com.example.listycitylab3;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.listycitylab3.databinding.FragmentAddCityBinding;

public class AddCityFragment extends DialogFragment {
    interface AddCityDialogListener{ // create a contract between the fragment and the activity such that context must implement this function for when user submits
        void addCity(City city);
        void editCity(City city, int position);
    }

    private AddCityDialogListener listener; // holds a reference to callback

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddCityDialogListener) { // checks if context implements the interface
            listener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement AddCityDialogListener");
        };
    }

    @NonNull
    @Override
    // creates the dialog. sets all the buttons up and inflates the fragment layout
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        FragmentAddCityBinding binding = FragmentAddCityBinding.inflate(getLayoutInflater());

        // Check if we got arguments
        Bundle args = getArguments();
        int position = -1;
        if (args != null) {
            String cityName = args.getString("city");
            String provinceName = args.getString("province");
            position = args.getInt("position", -1);

            // Pre-fill fields
            binding.cityInput.setText(cityName);
            binding.provinceInput.setText(provinceName);
        }

        int finalPosition = position;
        EditText cityInput = binding.cityInput;
        EditText provinceInput = binding.provinceInput;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(binding.getRoot())
                .setTitle(finalPosition == -1 ? "Add City" : "Edit City")
                .setNegativeButton("Cancel", null)
                .setPositiveButton(finalPosition == -1 ? "Add" : "Save", (dialog, which) -> {
                    String cityName = cityInput.getText().toString();
                    String provinceName = provinceInput.getText().toString();
                    City city = new City(cityName, provinceName);
                    if (finalPosition == -1) {
                        listener.addCity(city);
                    }else{
                        listener.editCity(city, finalPosition);
                    }
                })
                .create();
    }

    // Helper factory method for editing. Creates a bundle for sharing pre shared data with this dialog
    public static AddCityFragment newInstance(int position, City city) {
        AddCityFragment fragment = new AddCityFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putString("city", city.getName());
        args.putString("province", city.getProvince());
        fragment.setArguments(args);
        return fragment;
    }
}
