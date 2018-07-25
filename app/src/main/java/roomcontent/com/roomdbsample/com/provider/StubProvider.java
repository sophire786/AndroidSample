package roomcontent.com.roomdbsample.com.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import roomcontent.com.roomdbsample.com.ado.WordDao;
import roomcontent.com.roomdbsample.com.database.WordRoomDatabase;
import roomcontent.com.roomdbsample.com.entity.Word;

public class StubProvider extends ContentProvider {

    /** The authority of this content provider. */
    public static final String AUTHORITY = "roomcontent.com.roomdbsample.com.provider";

    /** The URI for the Cheese table. */
    public static final Uri URI_WORD = Uri.parse(
            "content://" + AUTHORITY + "/" + Word.TABLE_NAME);

    /** The match code for some items in the Cheese table. */
    private static final int CODE_CHEESE_DIR = 1;

    /** The match code for an item in the Cheese table. */
    private static final int CODE_CHEESE_ITEM = 2;

    /** The URI matcher. */
    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, Word.TABLE_NAME, CODE_CHEESE_DIR);
        MATCHER.addURI(AUTHORITY, Word.TABLE_NAME + "/*", CODE_CHEESE_ITEM);
    }

    /*
     * Always return true, indicating that the
     * provider loaded correctly.
     */
    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
            @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        WordDao cheese = WordRoomDatabase.getDatabase(getContext()).wordDao();
        Cursor cursor = cheese.selectAll();
        return cursor;
    }

    /*
     * Return no type for MIME type
     */
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection,
            @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
            @Nullable String[] selectionArgs) {
        return 0;
    }
}
