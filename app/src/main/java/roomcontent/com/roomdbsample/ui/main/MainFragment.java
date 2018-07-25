package roomcontent.com.roomdbsample.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import roomcontent.com.roomdbsample.R;
import roomcontent.com.roomdbsample.WordListAdapter;
import roomcontent.com.roomdbsample.com.entity.Word;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private View mView;
    private Button addBtn;
    private EditText mEditText;

    public static MainFragment newInstance() {

        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.main_fragment, container, false);
        }
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        RecyclerView recyclerView = mView.findViewById(R.id.recyclerview);
        mEditText = (EditText) mView.findViewById(R.id.editadd);
        addBtn = (Button) mView.findViewById(R.id.addbtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strWord = mEditText.getText().toString();
                Word word = new Word(strWord);
                mViewModel.insert(word);
            }
        });

        final WordListAdapter adapter = new WordListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // TODO: Use the ViewModel

        mViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable final List<Word> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setWords(words);
            }
        });
    }

}
