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

import com.niksplay.moviesland.Constants;
import com.niksplay.moviesland.R;
import com.niksplay.moviesland.activity.MediaDetailActivity;
import com.niksplay.moviesland.adapter.RecyclerItemsAdapter;
import com.niksplay.moviesland.adapter.holder.CreditPagerHolder;
import com.niksplay.moviesland.adapter.item.IListItem;
import com.niksplay.moviesland.adapter.item.ItemLabel;
import com.niksplay.moviesland.adapter.item.ItemLoader;
import com.niksplay.moviesland.adapter.item.ItemPagerCredits;
import com.niksplay.moviesland.adapter.item.ItemPersonHeader;
import com.niksplay.moviesland.app.App;
import com.niksplay.moviesland.model.Credit;
import com.niksplay.moviesland.model.Person;
import com.niksplay.moviesland.model.PersonDetailInfo;
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
public class PersonFragment extends Fragment implements LoaderManager.LoaderCallbacks<PersonDetailInfo> {

    private static final String EXTRA_PERSON = "person";

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private RecyclerItemsAdapter mAdapter;

    private long mPersonId;
    private PersonDetailInfo mPerson;

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
        Person person = getArguments().getParcelable(EXTRA_PERSON);
        if (person != null) {
            getActivity().setTitle(person.name);
            mPersonId = person.id;
        }
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

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter = new RecyclerItemsAdapter());

        invalidate();
    }

    @Override
    public Loader<PersonDetailInfo> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<PersonDetailInfo>(getActivity()) {
            @Override
            public PersonDetailInfo loadInBackground() {
                try {
                    Response<PersonDetailInfo> response = App.getInstance().getApiClient().getPerson(mPersonId, Constants.PARAM_COMBINED_CREDITS).execute();
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
    public void onLoadFinished(Loader<PersonDetailInfo> loader, PersonDetailInfo data) {
        mLoading = false;
        if (data != null) {
            mPerson = data;
        } else {
            Toast.makeText(getActivity(), R.string.error_getting_content, Toast.LENGTH_SHORT).show();
        }
        invalidate();
    }

    @Override
    public void onLoaderReset(Loader<PersonDetailInfo> loader) {

    }

    private void invalidate() {
        List<IListItem> items = new ArrayList<>();
        if (mLoading) {
            items.add(new ItemLoader());
        } else {
            items.add(new ItemPersonHeader(mPerson));
            if (mPerson.combinedCredits != null) {
                if (!ArrayUtils.isEmpty(mPerson.combinedCredits.cast)) {
                    items.add(new ItemLabel(getString(R.string.label_cast)));
                    items.add(new ItemPagerCredits(mPerson.combinedCredits.cast, mCreditItemSelectedListener));
                }

                if (!ArrayUtils.isEmpty(mPerson.combinedCredits.crew)) {
                    items.add(new ItemLabel(getString(R.string.label_crew)));
                    items.add(new ItemPagerCredits(mPerson.combinedCredits.crew, mCreditItemSelectedListener));
                }

            }
        }

        mAdapter.setData(items);
    }

    private CreditPagerHolder.OnItemSelectedListener mCreditItemSelectedListener = new CreditPagerHolder.OnItemSelectedListener() {
        @Override
        public void onItemSelected(Credit credit) {
            startActivity(MediaDetailActivity.createIntent(getActivity(), credit.createMedia()));
        }
    };
}
