package android.room.view.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.room.view.databinding.ActivityNewWordBinding;
import android.text.TextUtils;

public class NewWordActivity extends AppCompatActivity {

    ActivityNewWordBinding binding;
    static final String EXTRA_REPLY = "android.room.view";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityNewWordBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        String mEditWordView = binding.editWord.getText().toString();

        binding.buttonSave.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditWordView)){
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                replyIntent.putExtra(EXTRA_REPLY, mEditWordView);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}