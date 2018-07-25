package roomcontent.com.roomdatabaseacess.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import roomcontent.com.roomdatabaseacess.R;
import roomcontent.com.roomdatabaseacess.WordListAdapter;
import roomcontent.com.roomdatabaseacess.model.Word;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private View mView;
    public static MainFragment newInstance() {
        return new MainFragment();
    }
    private static final int LOADER_CHEESES = 1;
    CheeseAdapter adapter;
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
        // TODO: Use the ViewModel
        RecyclerView recyclerView = mView.findViewById(R.id.recyclerview);
         adapter = new CheeseAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getActivity().getSupportLoaderManager().initLoader(LOADER_CHEESES, null, mLoaderCallbacks);
//        mViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
//            @Override
//            public void onChanged(@Nullable final List<Word> words) {
//                // Update the cached copy of the words in the adapter.
//                adapter.setWords(words);
//            }
//        });
    }
    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallbacks =
            new LoaderManager.LoaderCallbacks<Cursor>() {

                @Override
                public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                    switch (id) {
                        case LOADER_CHEESES:
                          //  Uri uri=Uri.parse("content://com.example.android.contentprovidersample.provider/cheeses");
                            Uri uri=Uri.parse("content://roomcontent.com.roomdbsample.com.provider/word_table");

                            return new CursorLoader(getActivity().getApplicationContext(),
                                    uri,
                                    new String[]{"name"},
                                    null, null, null);
                        default:
                            throw new IllegalArgumentException();
                    }
                }

                @Override
                public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                    switch (loader.getId()) {
                        case LOADER_CHEESES:
                          //  Log.d("MainFragment","Count of cursor "+data.getCount());
                            adapter.setCheeses(data);
                            break;
                    }
                }

                @Override
                public void onLoaderReset(Loader<Cursor> loader) {
                    switch (loader.getId()) {
                        case LOADER_CHEESES:
                            adapter.setCheeses(null);
                            break;
                    }
                }

            };

    private static class CheeseAdapter extends RecyclerView.Adapter<CheeseAdapter.ViewHolder> {

        private Cursor mCursor;

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (mCursor.moveToPosition(position)) {
                holder.mText.setText(mCursor.getString(
                        mCursor.getColumnIndex("word")));
            }
        }

        @Override
        public int getItemCount() {
            return mCursor == null ? 0 : mCursor.getCount();
        }

        void setCheeses(Cursor cursor) {
            mCursor = cursor;
            notifyDataSetChanged();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {

            TextView mText;

            ViewHolder(ViewGroup parent) {
                super(LayoutInflater.from(parent.getContext()).inflate(
                        android.R.layout.simple_list_item_1, parent, false));
                mText = itemView.findViewById(android.R.id.text1);
            }

        }

    }
}
