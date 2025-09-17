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
        EditText cityInput = binding.cityInput;
        EditText provinceInput = binding.provinceInput;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(binding.getRoot())
                .setTitle("Add a city")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Add", (dialog, which) -> {
                    String cityName = cityInput.getText().toString();
                    String provinceName = provinceInput.getText().toString();
                    listener.addCity(new City(cityName, provinceName));
                })
                .create();
    }
}
