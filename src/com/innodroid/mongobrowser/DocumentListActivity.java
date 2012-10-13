package com.innodroid.mongobrowser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class DocumentListActivity extends FragmentActivity implements DocumentListFragment.Callbacks {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String name = getIntent().getStringExtra(Constants.ARG_COLLECTION_NAME);
        setTitle(name);
        setContentView(R.layout.activity_document_list);
        
        if (savedInstanceState == null) {
        	Bundle args = getIntent().getExtras();
	        DocumentListFragment fragment = new DocumentListFragment();
	        args.putBoolean(Constants.ARG_ACTIVATE_ON_CLICK, false);
	        fragment.setArguments(args);
	        getSupportFragmentManager().beginTransaction().add(R.id.document_list, fragment).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    		case android.R.id.home:
                NavUtils.navigateUpTo(this, new Intent(this, CollectionListActivity.class));
                return true;
    		default:
               	break;
    	}

        return super.onOptionsItemSelected(item);
    }
    
	@Override
    public void onDocumentItemSelected(int position, String content) {
		Intent intent = new Intent(this, DocumentDetailActivity.class);
		intent.putExtra(Constants.ARG_DOCUMENT_CONTENT, content);
		startActivity(intent);
    }

	@Override
	public void onCollectionEdited(String name) {
		setTitle(name);
	}

	@Override
	public void onCollectionDropped(String name) {
		finish();
	}
}
