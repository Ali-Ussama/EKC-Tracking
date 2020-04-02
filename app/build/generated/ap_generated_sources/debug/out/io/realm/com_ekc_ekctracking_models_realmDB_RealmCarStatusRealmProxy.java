package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.ImportFlag;
import io.realm.ProxyUtils;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsList;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.Property;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.internal.objectstore.OsObjectBuilder;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("all")
public class com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxy extends com.ekc.ekctracking.models.realmDB.RealmCarStatus
    implements RealmObjectProxy, com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface {

    static final class RealmCarStatusColumnInfo extends ColumnInfo {
        long maxColumnIndexValue;
        long carNoIndex;
        long GPSUnitNumberIndex;
        long disable_countIndex;
        long carIDIndex;
        long latitudeIndex;
        long longitudeIndex;
        long statusIndex;
        long speedIndex;
        long addressIndex;
        long dateIndex;
        long timeIndex;
        long speed2Index;
        long gpsUnitIndex;
        long driverNameIndex;
        long dataTimeIndex;
        long angleIndex;

        RealmCarStatusColumnInfo(OsSchemaInfo schemaInfo) {
            super(16);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("RealmCarStatus");
            this.carNoIndex = addColumnDetails("carNo", "carNo", objectSchemaInfo);
            this.GPSUnitNumberIndex = addColumnDetails("GPSUnitNumber", "GPSUnitNumber", objectSchemaInfo);
            this.disable_countIndex = addColumnDetails("disable_count", "disable_count", objectSchemaInfo);
            this.carIDIndex = addColumnDetails("carID", "carID", objectSchemaInfo);
            this.latitudeIndex = addColumnDetails("latitude", "latitude", objectSchemaInfo);
            this.longitudeIndex = addColumnDetails("longitude", "longitude", objectSchemaInfo);
            this.statusIndex = addColumnDetails("status", "status", objectSchemaInfo);
            this.speedIndex = addColumnDetails("speed", "speed", objectSchemaInfo);
            this.addressIndex = addColumnDetails("address", "address", objectSchemaInfo);
            this.dateIndex = addColumnDetails("date", "date", objectSchemaInfo);
            this.timeIndex = addColumnDetails("time", "time", objectSchemaInfo);
            this.speed2Index = addColumnDetails("speed2", "speed2", objectSchemaInfo);
            this.gpsUnitIndex = addColumnDetails("gpsUnit", "gpsUnit", objectSchemaInfo);
            this.driverNameIndex = addColumnDetails("driverName", "driverName", objectSchemaInfo);
            this.dataTimeIndex = addColumnDetails("dataTime", "dataTime", objectSchemaInfo);
            this.angleIndex = addColumnDetails("angle", "angle", objectSchemaInfo);
            this.maxColumnIndexValue = objectSchemaInfo.getMaxColumnIndex();
        }

        RealmCarStatusColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new RealmCarStatusColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final RealmCarStatusColumnInfo src = (RealmCarStatusColumnInfo) rawSrc;
            final RealmCarStatusColumnInfo dst = (RealmCarStatusColumnInfo) rawDst;
            dst.carNoIndex = src.carNoIndex;
            dst.GPSUnitNumberIndex = src.GPSUnitNumberIndex;
            dst.disable_countIndex = src.disable_countIndex;
            dst.carIDIndex = src.carIDIndex;
            dst.latitudeIndex = src.latitudeIndex;
            dst.longitudeIndex = src.longitudeIndex;
            dst.statusIndex = src.statusIndex;
            dst.speedIndex = src.speedIndex;
            dst.addressIndex = src.addressIndex;
            dst.dateIndex = src.dateIndex;
            dst.timeIndex = src.timeIndex;
            dst.speed2Index = src.speed2Index;
            dst.gpsUnitIndex = src.gpsUnitIndex;
            dst.driverNameIndex = src.driverNameIndex;
            dst.dataTimeIndex = src.dataTimeIndex;
            dst.angleIndex = src.angleIndex;
            dst.maxColumnIndexValue = src.maxColumnIndexValue;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private RealmCarStatusColumnInfo columnInfo;
    private ProxyState<com.ekc.ekctracking.models.realmDB.RealmCarStatus> proxyState;

    com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (RealmCarStatusColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.ekc.ekctracking.models.realmDB.RealmCarStatus>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$carNo() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.carNoIndex);
    }

    @Override
    public void realmSet$carNo(String value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'carNo' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$GPSUnitNumber() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.GPSUnitNumberIndex);
    }

    @Override
    public void realmSet$GPSUnitNumber(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.GPSUnitNumberIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.GPSUnitNumberIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.GPSUnitNumberIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.GPSUnitNumberIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$disable_count() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.disable_countIndex);
    }

    @Override
    public void realmSet$disable_count(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.disable_countIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.disable_countIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$carID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.carIDIndex);
    }

    @Override
    public void realmSet$carID(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.carIDIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.carIDIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.carIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.carIDIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public double realmGet$latitude() {
        proxyState.getRealm$realm().checkIfValid();
        return (double) proxyState.getRow$realm().getDouble(columnInfo.latitudeIndex);
    }

    @Override
    public void realmSet$latitude(double value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setDouble(columnInfo.latitudeIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setDouble(columnInfo.latitudeIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public double realmGet$longitude() {
        proxyState.getRealm$realm().checkIfValid();
        return (double) proxyState.getRow$realm().getDouble(columnInfo.longitudeIndex);
    }

    @Override
    public void realmSet$longitude(double value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setDouble(columnInfo.longitudeIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setDouble(columnInfo.longitudeIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$status() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.statusIndex);
    }

    @Override
    public void realmSet$status(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.statusIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.statusIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.statusIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.statusIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$speed() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.speedIndex);
    }

    @Override
    public void realmSet$speed(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.speedIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.speedIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.speedIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.speedIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$address() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.addressIndex);
    }

    @Override
    public void realmSet$address(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.addressIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.addressIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.addressIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.addressIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$date() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.dateIndex);
    }

    @Override
    public void realmSet$date(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.dateIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.dateIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.dateIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.dateIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$time() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.timeIndex);
    }

    @Override
    public void realmSet$time(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.timeIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.timeIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.timeIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.timeIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$speed2() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.speed2Index);
    }

    @Override
    public void realmSet$speed2(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.speed2Index, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.speed2Index, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.speed2Index);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.speed2Index, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$gpsUnit() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.gpsUnitIndex);
    }

    @Override
    public void realmSet$gpsUnit(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.gpsUnitIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.gpsUnitIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.gpsUnitIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.gpsUnitIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$driverName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.driverNameIndex);
    }

    @Override
    public void realmSet$driverName(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.driverNameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.driverNameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.driverNameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.driverNameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$dataTime() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.dataTimeIndex);
    }

    @Override
    public void realmSet$dataTime(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.dataTimeIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.dataTimeIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.dataTimeIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.dataTimeIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public double realmGet$angle() {
        proxyState.getRealm$realm().checkIfValid();
        return (double) proxyState.getRow$realm().getDouble(columnInfo.angleIndex);
    }

    @Override
    public void realmSet$angle(double value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setDouble(columnInfo.angleIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setDouble(columnInfo.angleIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("RealmCarStatus", 16, 0);
        builder.addPersistedProperty("carNo", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("GPSUnitNumber", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("disable_count", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("carID", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("latitude", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("longitude", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("status", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("speed", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("address", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("date", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("time", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("speed2", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("gpsUnit", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("driverName", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("dataTime", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("angle", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static RealmCarStatusColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new RealmCarStatusColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "RealmCarStatus";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "RealmCarStatus";
    }

    @SuppressWarnings("cast")
    public static com.ekc.ekctracking.models.realmDB.RealmCarStatus createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.ekc.ekctracking.models.realmDB.RealmCarStatus obj = null;
        if (update) {
            Table table = realm.getTable(com.ekc.ekctracking.models.realmDB.RealmCarStatus.class);
            RealmCarStatusColumnInfo columnInfo = (RealmCarStatusColumnInfo) realm.getSchema().getColumnInfo(com.ekc.ekctracking.models.realmDB.RealmCarStatus.class);
            long pkColumnIndex = columnInfo.carNoIndex;
            long rowIndex = Table.NO_MATCH;
            if (json.isNull("carNo")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("carNo"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.ekc.ekctracking.models.realmDB.RealmCarStatus.class), false, Collections.<String> emptyList());
                    obj = new io.realm.com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("carNo")) {
                if (json.isNull("carNo")) {
                    obj = (io.realm.com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxy) realm.createObjectInternal(com.ekc.ekctracking.models.realmDB.RealmCarStatus.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxy) realm.createObjectInternal(com.ekc.ekctracking.models.realmDB.RealmCarStatus.class, json.getString("carNo"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'carNo'.");
            }
        }

        final com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface objProxy = (com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) obj;
        if (json.has("GPSUnitNumber")) {
            if (json.isNull("GPSUnitNumber")) {
                objProxy.realmSet$GPSUnitNumber(null);
            } else {
                objProxy.realmSet$GPSUnitNumber((String) json.getString("GPSUnitNumber"));
            }
        }
        if (json.has("disable_count")) {
            if (json.isNull("disable_count")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'disable_count' to null.");
            } else {
                objProxy.realmSet$disable_count((int) json.getInt("disable_count"));
            }
        }
        if (json.has("carID")) {
            if (json.isNull("carID")) {
                objProxy.realmSet$carID(null);
            } else {
                objProxy.realmSet$carID((String) json.getString("carID"));
            }
        }
        if (json.has("latitude")) {
            if (json.isNull("latitude")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'latitude' to null.");
            } else {
                objProxy.realmSet$latitude((double) json.getDouble("latitude"));
            }
        }
        if (json.has("longitude")) {
            if (json.isNull("longitude")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'longitude' to null.");
            } else {
                objProxy.realmSet$longitude((double) json.getDouble("longitude"));
            }
        }
        if (json.has("status")) {
            if (json.isNull("status")) {
                objProxy.realmSet$status(null);
            } else {
                objProxy.realmSet$status((String) json.getString("status"));
            }
        }
        if (json.has("speed")) {
            if (json.isNull("speed")) {
                objProxy.realmSet$speed(null);
            } else {
                objProxy.realmSet$speed((String) json.getString("speed"));
            }
        }
        if (json.has("address")) {
            if (json.isNull("address")) {
                objProxy.realmSet$address(null);
            } else {
                objProxy.realmSet$address((String) json.getString("address"));
            }
        }
        if (json.has("date")) {
            if (json.isNull("date")) {
                objProxy.realmSet$date(null);
            } else {
                objProxy.realmSet$date((String) json.getString("date"));
            }
        }
        if (json.has("time")) {
            if (json.isNull("time")) {
                objProxy.realmSet$time(null);
            } else {
                objProxy.realmSet$time((String) json.getString("time"));
            }
        }
        if (json.has("speed2")) {
            if (json.isNull("speed2")) {
                objProxy.realmSet$speed2(null);
            } else {
                objProxy.realmSet$speed2((String) json.getString("speed2"));
            }
        }
        if (json.has("gpsUnit")) {
            if (json.isNull("gpsUnit")) {
                objProxy.realmSet$gpsUnit(null);
            } else {
                objProxy.realmSet$gpsUnit((String) json.getString("gpsUnit"));
            }
        }
        if (json.has("driverName")) {
            if (json.isNull("driverName")) {
                objProxy.realmSet$driverName(null);
            } else {
                objProxy.realmSet$driverName((String) json.getString("driverName"));
            }
        }
        if (json.has("dataTime")) {
            if (json.isNull("dataTime")) {
                objProxy.realmSet$dataTime(null);
            } else {
                objProxy.realmSet$dataTime((String) json.getString("dataTime"));
            }
        }
        if (json.has("angle")) {
            if (json.isNull("angle")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'angle' to null.");
            } else {
                objProxy.realmSet$angle((double) json.getDouble("angle"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.ekc.ekctracking.models.realmDB.RealmCarStatus createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.ekc.ekctracking.models.realmDB.RealmCarStatus obj = new com.ekc.ekctracking.models.realmDB.RealmCarStatus();
        final com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface objProxy = (com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("carNo")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$carNo((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$carNo(null);
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("GPSUnitNumber")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$GPSUnitNumber((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$GPSUnitNumber(null);
                }
            } else if (name.equals("disable_count")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$disable_count((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'disable_count' to null.");
                }
            } else if (name.equals("carID")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$carID((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$carID(null);
                }
            } else if (name.equals("latitude")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$latitude((double) reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'latitude' to null.");
                }
            } else if (name.equals("longitude")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$longitude((double) reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'longitude' to null.");
                }
            } else if (name.equals("status")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$status((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$status(null);
                }
            } else if (name.equals("speed")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$speed((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$speed(null);
                }
            } else if (name.equals("address")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$address((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$address(null);
                }
            } else if (name.equals("date")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$date((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$date(null);
                }
            } else if (name.equals("time")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$time((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$time(null);
                }
            } else if (name.equals("speed2")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$speed2((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$speed2(null);
                }
            } else if (name.equals("gpsUnit")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$gpsUnit((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$gpsUnit(null);
                }
            } else if (name.equals("driverName")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$driverName((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$driverName(null);
                }
            } else if (name.equals("dataTime")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$dataTime((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$dataTime(null);
                }
            } else if (name.equals("angle")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$angle((double) reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'angle' to null.");
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'carNo'.");
        }
        return realm.copyToRealm(obj);
    }

    private static com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.ekc.ekctracking.models.realmDB.RealmCarStatus.class), false, Collections.<String>emptyList());
        io.realm.com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxy obj = new io.realm.com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.ekc.ekctracking.models.realmDB.RealmCarStatus copyOrUpdate(Realm realm, RealmCarStatusColumnInfo columnInfo, com.ekc.ekctracking.models.realmDB.RealmCarStatus object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null) {
            final BaseRealm otherRealm = ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm();
            if (otherRealm.threadId != realm.threadId) {
                throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
            }
            if (otherRealm.getPath().equals(realm.getPath())) {
                return object;
            }
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.ekc.ekctracking.models.realmDB.RealmCarStatus) cachedRealmObject;
        }

        com.ekc.ekctracking.models.realmDB.RealmCarStatus realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.ekc.ekctracking.models.realmDB.RealmCarStatus.class);
            long pkColumnIndex = columnInfo.carNoIndex;
            String value = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$carNo();
            long rowIndex = Table.NO_MATCH;
            if (value == null) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, value);
            }
            if (rowIndex == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), columnInfo, false, Collections.<String> emptyList());
                    realmObject = new io.realm.com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, columnInfo, realmObject, object, cache, flags) : copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.ekc.ekctracking.models.realmDB.RealmCarStatus copy(Realm realm, RealmCarStatusColumnInfo columnInfo, com.ekc.ekctracking.models.realmDB.RealmCarStatus newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.ekc.ekctracking.models.realmDB.RealmCarStatus) cachedRealmObject;
        }

        com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface realmObjectSource = (com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) newObject;

        Table table = realm.getTable(com.ekc.ekctracking.models.realmDB.RealmCarStatus.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);

        // Add all non-"object reference" fields
        builder.addString(columnInfo.carNoIndex, realmObjectSource.realmGet$carNo());
        builder.addString(columnInfo.GPSUnitNumberIndex, realmObjectSource.realmGet$GPSUnitNumber());
        builder.addInteger(columnInfo.disable_countIndex, realmObjectSource.realmGet$disable_count());
        builder.addString(columnInfo.carIDIndex, realmObjectSource.realmGet$carID());
        builder.addDouble(columnInfo.latitudeIndex, realmObjectSource.realmGet$latitude());
        builder.addDouble(columnInfo.longitudeIndex, realmObjectSource.realmGet$longitude());
        builder.addString(columnInfo.statusIndex, realmObjectSource.realmGet$status());
        builder.addString(columnInfo.speedIndex, realmObjectSource.realmGet$speed());
        builder.addString(columnInfo.addressIndex, realmObjectSource.realmGet$address());
        builder.addString(columnInfo.dateIndex, realmObjectSource.realmGet$date());
        builder.addString(columnInfo.timeIndex, realmObjectSource.realmGet$time());
        builder.addString(columnInfo.speed2Index, realmObjectSource.realmGet$speed2());
        builder.addString(columnInfo.gpsUnitIndex, realmObjectSource.realmGet$gpsUnit());
        builder.addString(columnInfo.driverNameIndex, realmObjectSource.realmGet$driverName());
        builder.addString(columnInfo.dataTimeIndex, realmObjectSource.realmGet$dataTime());
        builder.addDouble(columnInfo.angleIndex, realmObjectSource.realmGet$angle());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxy realmObjectCopy = newProxyInstance(realm, row);
        cache.put(newObject, realmObjectCopy);

        return realmObjectCopy;
    }

    public static long insert(Realm realm, com.ekc.ekctracking.models.realmDB.RealmCarStatus object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.ekc.ekctracking.models.realmDB.RealmCarStatus.class);
        long tableNativePtr = table.getNativePtr();
        RealmCarStatusColumnInfo columnInfo = (RealmCarStatusColumnInfo) realm.getSchema().getColumnInfo(com.ekc.ekctracking.models.realmDB.RealmCarStatus.class);
        long pkColumnIndex = columnInfo.carNoIndex;
        String primaryKeyValue = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$carNo();
        long rowIndex = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$GPSUnitNumber = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$GPSUnitNumber();
        if (realmGet$GPSUnitNumber != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.GPSUnitNumberIndex, rowIndex, realmGet$GPSUnitNumber, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.disable_countIndex, rowIndex, ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$disable_count(), false);
        String realmGet$carID = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$carID();
        if (realmGet$carID != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.carIDIndex, rowIndex, realmGet$carID, false);
        }
        Table.nativeSetDouble(tableNativePtr, columnInfo.latitudeIndex, rowIndex, ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$latitude(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.longitudeIndex, rowIndex, ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$longitude(), false);
        String realmGet$status = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$status();
        if (realmGet$status != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.statusIndex, rowIndex, realmGet$status, false);
        }
        String realmGet$speed = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$speed();
        if (realmGet$speed != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.speedIndex, rowIndex, realmGet$speed, false);
        }
        String realmGet$address = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$address();
        if (realmGet$address != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.addressIndex, rowIndex, realmGet$address, false);
        }
        String realmGet$date = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$date();
        if (realmGet$date != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date, false);
        }
        String realmGet$time = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$time();
        if (realmGet$time != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.timeIndex, rowIndex, realmGet$time, false);
        }
        String realmGet$speed2 = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$speed2();
        if (realmGet$speed2 != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.speed2Index, rowIndex, realmGet$speed2, false);
        }
        String realmGet$gpsUnit = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$gpsUnit();
        if (realmGet$gpsUnit != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.gpsUnitIndex, rowIndex, realmGet$gpsUnit, false);
        }
        String realmGet$driverName = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$driverName();
        if (realmGet$driverName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.driverNameIndex, rowIndex, realmGet$driverName, false);
        }
        String realmGet$dataTime = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$dataTime();
        if (realmGet$dataTime != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dataTimeIndex, rowIndex, realmGet$dataTime, false);
        }
        Table.nativeSetDouble(tableNativePtr, columnInfo.angleIndex, rowIndex, ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$angle(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.ekc.ekctracking.models.realmDB.RealmCarStatus.class);
        long tableNativePtr = table.getNativePtr();
        RealmCarStatusColumnInfo columnInfo = (RealmCarStatusColumnInfo) realm.getSchema().getColumnInfo(com.ekc.ekctracking.models.realmDB.RealmCarStatus.class);
        long pkColumnIndex = columnInfo.carNoIndex;
        com.ekc.ekctracking.models.realmDB.RealmCarStatus object = null;
        while (objects.hasNext()) {
            object = (com.ekc.ekctracking.models.realmDB.RealmCarStatus) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            String primaryKeyValue = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$carNo();
            long rowIndex = Table.NO_MATCH;
            if (primaryKeyValue == null) {
                rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
            } else {
                rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, rowIndex);
            String realmGet$GPSUnitNumber = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$GPSUnitNumber();
            if (realmGet$GPSUnitNumber != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.GPSUnitNumberIndex, rowIndex, realmGet$GPSUnitNumber, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.disable_countIndex, rowIndex, ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$disable_count(), false);
            String realmGet$carID = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$carID();
            if (realmGet$carID != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.carIDIndex, rowIndex, realmGet$carID, false);
            }
            Table.nativeSetDouble(tableNativePtr, columnInfo.latitudeIndex, rowIndex, ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$latitude(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.longitudeIndex, rowIndex, ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$longitude(), false);
            String realmGet$status = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$status();
            if (realmGet$status != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.statusIndex, rowIndex, realmGet$status, false);
            }
            String realmGet$speed = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$speed();
            if (realmGet$speed != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.speedIndex, rowIndex, realmGet$speed, false);
            }
            String realmGet$address = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$address();
            if (realmGet$address != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.addressIndex, rowIndex, realmGet$address, false);
            }
            String realmGet$date = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$date();
            if (realmGet$date != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date, false);
            }
            String realmGet$time = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$time();
            if (realmGet$time != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.timeIndex, rowIndex, realmGet$time, false);
            }
            String realmGet$speed2 = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$speed2();
            if (realmGet$speed2 != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.speed2Index, rowIndex, realmGet$speed2, false);
            }
            String realmGet$gpsUnit = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$gpsUnit();
            if (realmGet$gpsUnit != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.gpsUnitIndex, rowIndex, realmGet$gpsUnit, false);
            }
            String realmGet$driverName = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$driverName();
            if (realmGet$driverName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.driverNameIndex, rowIndex, realmGet$driverName, false);
            }
            String realmGet$dataTime = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$dataTime();
            if (realmGet$dataTime != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.dataTimeIndex, rowIndex, realmGet$dataTime, false);
            }
            Table.nativeSetDouble(tableNativePtr, columnInfo.angleIndex, rowIndex, ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$angle(), false);
        }
    }

    public static long insertOrUpdate(Realm realm, com.ekc.ekctracking.models.realmDB.RealmCarStatus object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.ekc.ekctracking.models.realmDB.RealmCarStatus.class);
        long tableNativePtr = table.getNativePtr();
        RealmCarStatusColumnInfo columnInfo = (RealmCarStatusColumnInfo) realm.getSchema().getColumnInfo(com.ekc.ekctracking.models.realmDB.RealmCarStatus.class);
        long pkColumnIndex = columnInfo.carNoIndex;
        String primaryKeyValue = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$carNo();
        long rowIndex = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$GPSUnitNumber = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$GPSUnitNumber();
        if (realmGet$GPSUnitNumber != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.GPSUnitNumberIndex, rowIndex, realmGet$GPSUnitNumber, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.GPSUnitNumberIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.disable_countIndex, rowIndex, ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$disable_count(), false);
        String realmGet$carID = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$carID();
        if (realmGet$carID != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.carIDIndex, rowIndex, realmGet$carID, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.carIDIndex, rowIndex, false);
        }
        Table.nativeSetDouble(tableNativePtr, columnInfo.latitudeIndex, rowIndex, ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$latitude(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.longitudeIndex, rowIndex, ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$longitude(), false);
        String realmGet$status = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$status();
        if (realmGet$status != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.statusIndex, rowIndex, realmGet$status, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.statusIndex, rowIndex, false);
        }
        String realmGet$speed = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$speed();
        if (realmGet$speed != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.speedIndex, rowIndex, realmGet$speed, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.speedIndex, rowIndex, false);
        }
        String realmGet$address = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$address();
        if (realmGet$address != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.addressIndex, rowIndex, realmGet$address, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.addressIndex, rowIndex, false);
        }
        String realmGet$date = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$date();
        if (realmGet$date != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.dateIndex, rowIndex, false);
        }
        String realmGet$time = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$time();
        if (realmGet$time != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.timeIndex, rowIndex, realmGet$time, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.timeIndex, rowIndex, false);
        }
        String realmGet$speed2 = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$speed2();
        if (realmGet$speed2 != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.speed2Index, rowIndex, realmGet$speed2, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.speed2Index, rowIndex, false);
        }
        String realmGet$gpsUnit = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$gpsUnit();
        if (realmGet$gpsUnit != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.gpsUnitIndex, rowIndex, realmGet$gpsUnit, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.gpsUnitIndex, rowIndex, false);
        }
        String realmGet$driverName = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$driverName();
        if (realmGet$driverName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.driverNameIndex, rowIndex, realmGet$driverName, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.driverNameIndex, rowIndex, false);
        }
        String realmGet$dataTime = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$dataTime();
        if (realmGet$dataTime != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dataTimeIndex, rowIndex, realmGet$dataTime, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.dataTimeIndex, rowIndex, false);
        }
        Table.nativeSetDouble(tableNativePtr, columnInfo.angleIndex, rowIndex, ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$angle(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.ekc.ekctracking.models.realmDB.RealmCarStatus.class);
        long tableNativePtr = table.getNativePtr();
        RealmCarStatusColumnInfo columnInfo = (RealmCarStatusColumnInfo) realm.getSchema().getColumnInfo(com.ekc.ekctracking.models.realmDB.RealmCarStatus.class);
        long pkColumnIndex = columnInfo.carNoIndex;
        com.ekc.ekctracking.models.realmDB.RealmCarStatus object = null;
        while (objects.hasNext()) {
            object = (com.ekc.ekctracking.models.realmDB.RealmCarStatus) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            String primaryKeyValue = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$carNo();
            long rowIndex = Table.NO_MATCH;
            if (primaryKeyValue == null) {
                rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
            } else {
                rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
            }
            cache.put(object, rowIndex);
            String realmGet$GPSUnitNumber = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$GPSUnitNumber();
            if (realmGet$GPSUnitNumber != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.GPSUnitNumberIndex, rowIndex, realmGet$GPSUnitNumber, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.GPSUnitNumberIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.disable_countIndex, rowIndex, ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$disable_count(), false);
            String realmGet$carID = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$carID();
            if (realmGet$carID != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.carIDIndex, rowIndex, realmGet$carID, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.carIDIndex, rowIndex, false);
            }
            Table.nativeSetDouble(tableNativePtr, columnInfo.latitudeIndex, rowIndex, ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$latitude(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.longitudeIndex, rowIndex, ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$longitude(), false);
            String realmGet$status = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$status();
            if (realmGet$status != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.statusIndex, rowIndex, realmGet$status, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.statusIndex, rowIndex, false);
            }
            String realmGet$speed = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$speed();
            if (realmGet$speed != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.speedIndex, rowIndex, realmGet$speed, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.speedIndex, rowIndex, false);
            }
            String realmGet$address = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$address();
            if (realmGet$address != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.addressIndex, rowIndex, realmGet$address, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.addressIndex, rowIndex, false);
            }
            String realmGet$date = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$date();
            if (realmGet$date != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.dateIndex, rowIndex, false);
            }
            String realmGet$time = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$time();
            if (realmGet$time != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.timeIndex, rowIndex, realmGet$time, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.timeIndex, rowIndex, false);
            }
            String realmGet$speed2 = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$speed2();
            if (realmGet$speed2 != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.speed2Index, rowIndex, realmGet$speed2, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.speed2Index, rowIndex, false);
            }
            String realmGet$gpsUnit = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$gpsUnit();
            if (realmGet$gpsUnit != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.gpsUnitIndex, rowIndex, realmGet$gpsUnit, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.gpsUnitIndex, rowIndex, false);
            }
            String realmGet$driverName = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$driverName();
            if (realmGet$driverName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.driverNameIndex, rowIndex, realmGet$driverName, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.driverNameIndex, rowIndex, false);
            }
            String realmGet$dataTime = ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$dataTime();
            if (realmGet$dataTime != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.dataTimeIndex, rowIndex, realmGet$dataTime, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.dataTimeIndex, rowIndex, false);
            }
            Table.nativeSetDouble(tableNativePtr, columnInfo.angleIndex, rowIndex, ((com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) object).realmGet$angle(), false);
        }
    }

    public static com.ekc.ekctracking.models.realmDB.RealmCarStatus createDetachedCopy(com.ekc.ekctracking.models.realmDB.RealmCarStatus realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.ekc.ekctracking.models.realmDB.RealmCarStatus unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.ekc.ekctracking.models.realmDB.RealmCarStatus();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.ekc.ekctracking.models.realmDB.RealmCarStatus) cachedObject.object;
            }
            unmanagedObject = (com.ekc.ekctracking.models.realmDB.RealmCarStatus) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface unmanagedCopy = (com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) unmanagedObject;
        com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface realmSource = (com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$carNo(realmSource.realmGet$carNo());
        unmanagedCopy.realmSet$GPSUnitNumber(realmSource.realmGet$GPSUnitNumber());
        unmanagedCopy.realmSet$disable_count(realmSource.realmGet$disable_count());
        unmanagedCopy.realmSet$carID(realmSource.realmGet$carID());
        unmanagedCopy.realmSet$latitude(realmSource.realmGet$latitude());
        unmanagedCopy.realmSet$longitude(realmSource.realmGet$longitude());
        unmanagedCopy.realmSet$status(realmSource.realmGet$status());
        unmanagedCopy.realmSet$speed(realmSource.realmGet$speed());
        unmanagedCopy.realmSet$address(realmSource.realmGet$address());
        unmanagedCopy.realmSet$date(realmSource.realmGet$date());
        unmanagedCopy.realmSet$time(realmSource.realmGet$time());
        unmanagedCopy.realmSet$speed2(realmSource.realmGet$speed2());
        unmanagedCopy.realmSet$gpsUnit(realmSource.realmGet$gpsUnit());
        unmanagedCopy.realmSet$driverName(realmSource.realmGet$driverName());
        unmanagedCopy.realmSet$dataTime(realmSource.realmGet$dataTime());
        unmanagedCopy.realmSet$angle(realmSource.realmGet$angle());

        return unmanagedObject;
    }

    static com.ekc.ekctracking.models.realmDB.RealmCarStatus update(Realm realm, RealmCarStatusColumnInfo columnInfo, com.ekc.ekctracking.models.realmDB.RealmCarStatus realmObject, com.ekc.ekctracking.models.realmDB.RealmCarStatus newObject, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface realmObjectTarget = (com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) realmObject;
        com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface realmObjectSource = (com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxyInterface) newObject;
        Table table = realm.getTable(com.ekc.ekctracking.models.realmDB.RealmCarStatus.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);
        builder.addString(columnInfo.carNoIndex, realmObjectSource.realmGet$carNo());
        builder.addString(columnInfo.GPSUnitNumberIndex, realmObjectSource.realmGet$GPSUnitNumber());
        builder.addInteger(columnInfo.disable_countIndex, realmObjectSource.realmGet$disable_count());
        builder.addString(columnInfo.carIDIndex, realmObjectSource.realmGet$carID());
        builder.addDouble(columnInfo.latitudeIndex, realmObjectSource.realmGet$latitude());
        builder.addDouble(columnInfo.longitudeIndex, realmObjectSource.realmGet$longitude());
        builder.addString(columnInfo.statusIndex, realmObjectSource.realmGet$status());
        builder.addString(columnInfo.speedIndex, realmObjectSource.realmGet$speed());
        builder.addString(columnInfo.addressIndex, realmObjectSource.realmGet$address());
        builder.addString(columnInfo.dateIndex, realmObjectSource.realmGet$date());
        builder.addString(columnInfo.timeIndex, realmObjectSource.realmGet$time());
        builder.addString(columnInfo.speed2Index, realmObjectSource.realmGet$speed2());
        builder.addString(columnInfo.gpsUnitIndex, realmObjectSource.realmGet$gpsUnit());
        builder.addString(columnInfo.driverNameIndex, realmObjectSource.realmGet$driverName());
        builder.addString(columnInfo.dataTimeIndex, realmObjectSource.realmGet$dataTime());
        builder.addDouble(columnInfo.angleIndex, realmObjectSource.realmGet$angle());

        builder.updateExistingObject();
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("RealmCarStatus = proxy[");
        stringBuilder.append("{carNo:");
        stringBuilder.append(realmGet$carNo() != null ? realmGet$carNo() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{GPSUnitNumber:");
        stringBuilder.append(realmGet$GPSUnitNumber() != null ? realmGet$GPSUnitNumber() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{disable_count:");
        stringBuilder.append(realmGet$disable_count());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{carID:");
        stringBuilder.append(realmGet$carID() != null ? realmGet$carID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{latitude:");
        stringBuilder.append(realmGet$latitude());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{longitude:");
        stringBuilder.append(realmGet$longitude());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{status:");
        stringBuilder.append(realmGet$status() != null ? realmGet$status() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{speed:");
        stringBuilder.append(realmGet$speed() != null ? realmGet$speed() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{address:");
        stringBuilder.append(realmGet$address() != null ? realmGet$address() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{date:");
        stringBuilder.append(realmGet$date() != null ? realmGet$date() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{time:");
        stringBuilder.append(realmGet$time() != null ? realmGet$time() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{speed2:");
        stringBuilder.append(realmGet$speed2() != null ? realmGet$speed2() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{gpsUnit:");
        stringBuilder.append(realmGet$gpsUnit() != null ? realmGet$gpsUnit() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{driverName:");
        stringBuilder.append(realmGet$driverName() != null ? realmGet$driverName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{dataTime:");
        stringBuilder.append(realmGet$dataTime() != null ? realmGet$dataTime() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{angle:");
        stringBuilder.append(realmGet$angle());
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState<?> realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxy aRealmCarStatus = (com_ekc_ekctracking_models_realmDB_RealmCarStatusRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aRealmCarStatus.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aRealmCarStatus.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aRealmCarStatus.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
