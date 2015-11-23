package com.niksplay.moviesland.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.adapter.PersonDetailAdapter;
import com.niksplay.moviesland.adapter.item.IListItem;
import com.niksplay.moviesland.adapter.item.ItemLabel;
import com.niksplay.moviesland.adapter.item.ItemLoader;
import com.niksplay.moviesland.adapter.item.ItemPagerMedias;
import com.niksplay.moviesland.adapter.item.ItemPersonHeader;
import com.niksplay.moviesland.app.App;
import com.niksplay.moviesland.model.Person;
import com.niksplay.moviesland.utils.ArrayUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Response;

/**
 * Created by nikita on 22.11.15.
 */
public class PersonFragment extends Fragment implements LoaderManager.LoaderCallbacks<Person> {

    private static final String EXTRA_PERSON = "person";

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private PersonDetailAdapter mAdapter;

    private Person mPerson;

    private boolean mLoading;

    public static PersonFragment create(Person person) {
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_PERSON, person);
        PersonFragment fragment = new PersonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPerson = getArguments().getParcelable(EXTRA_PERSON);
        mLoading = true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_person, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        getActivity().setTitle(mPerson.name);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter = new PersonDetailAdapter());

        invalidate();
    }

    @Override
    public Loader<Person> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<Person>(getActivity()) {
            @Override
            public Person loadInBackground() {
                try {
                    Response<Person> response = App.getInstance().getApiClient().getPerson(mPerson.id).execute();
                    if (response.isSuccess()) {
                        return response.body();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                forceLoad();
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Person> loader, Person data) {
        mLoading = false;
        if (data != null) {
            mPerson.placeOfBirth = data.placeOfBirth;
            mPerson.birthday = data.birthday;
            mPerson.biography = data.biography;
        } else {
            Toast.makeText(getActivity(), R.string.error_getting_content, Toast.LENGTH_SHORT).show();
        }
        invalidate();
    }

    @Override
    public void onLoaderReset(Loader<Person> loader) {

    }

    private void invalidate() {
        List<IListItem> items = new ArrayList<>();
        if (mLoading) {
            items.add(new ItemLoader());
        } else {
            items.add(new ItemPersonHeader(mPerson));
            if(!ArrayUtils.isEmpty(mPerson.knownFor)){
                items.add(new ItemLabel(getString(R.string.label_known_for)));
                items.add(new ItemPagerMedias(mPerson.knownFor));
            }
        }

        mAdapter.setData(items);
    }
}
