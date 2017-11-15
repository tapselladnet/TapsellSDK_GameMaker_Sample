package ${YYAndroidPackageName};

import android.util.Log;

import ir.tapsell.sdk.TapsellGameMaker;
import ir.tapsell.sdk.TapsellGameMakerListener;

import ${YYAndroidPackageName}.R;
import com.yoyogames.runner.RunnerJNILib;
import ${YYAndroidPackageName}.RunnerActivity;

/**
 * Created by Sina Rezaei (sinarezaei1991@gmail.com) on 1/8/2017.
 */
public class Tapsell implements TapsellGameMakerListener{
	
	private static final String TOP = "1";
    private static final String BOTTOM = "2";
    private static final String LEFT = "3";
    private static final String RIGHT = "4";
    private static final String CENTER = "5";
	
	private static final String BANNER_320x50 = "1";
    private static final String BANNER_320x100 = "2";
    private static final String BANNER_250x250 = "3";
    private static final String BANNER_300x250 = "4";

	public String showAd(String id, String back_disabled, String immersive_mode, String rotation_mode, String show_dialog)
    {
		Log.e("Tapsell","showAd called");
        TapsellGameMaker.showAd(RunnerActivity.CurrentActivity,id,back_disabled.equalsIgnoreCase("true"),immersive_mode.equalsIgnoreCase("true"),Integer.parseInt(rotation_mode),show_dialog!=null && show_dialog.equalsIgnoreCase("true"));
		return "true";
    }

    public String setDebugMode(String debug)
    {
		Log.e("Tapsell","setDebugMode called");
        TapsellGameMaker.setDebugMode(RunnerActivity.CurrentActivity,debug.equalsIgnoreCase("true"));
		return "Done";
    }
	
	public String setAutoHandlePermissions(String handle)
    {
        TapsellGameMaker.setAutoHandlePermissions(RunnerActivity.CurrentActivity,handle!=null && handle.equalsIgnoreCase("true"));
		return "Done";
    }
	
	public String setPermissionHandlerConfig(String config)
    {
        TapsellGameMaker.setMaxAllowedBandwidthUsage(RunnerActivity.CurrentActivity,Integer.parseInt(config));
		return "Done";
    }
	
	public String setMaxAllowedBandwidthUsage(String maxBpsSpeed)
    {
        TapsellGameMaker.setMaxAllowedBandwidthUsage(RunnerActivity.CurrentActivity,Integer.parseInt(maxBpsSpeed));
		return "Done";
    }

    public String setMaxAllowedBandwidthUsagePercentage(String maxPercentage)
    {
        TapsellGameMaker.setMaxAllowedBandwidthUsagePercentage(RunnerActivity.CurrentActivity,Integer.parseInt(maxPercentage));
		return "Done";
    }

    public String clearBandwidthUsageConstrains()
    {
        TapsellGameMaker.clearBandwidthUsageConstrains(RunnerActivity.CurrentActivity);
		return "Done";
    }

    public String isDebugMode()
    {
		Log.e("Tapsell","isDebugMode called");
        return TapsellGameMaker.isDebugMode(RunnerActivity.CurrentActivity)==true ? "true" : "false";
    }

    public String setAppUserId(String appUserId)
    {
		Log.e("Tapsell","setAppUserId called");
        TapsellGameMaker.setAppUserId(RunnerActivity.CurrentActivity,appUserId);
		return "Done";
    }

    public String getAppUserId()
    {
		Log.e("Tapsell","getAppUserId called");
        return TapsellGameMaker.getAppUserId(RunnerActivity.CurrentActivity);
    }

    public String initialize( String appKey)
    {
		Log.e("Tapsell","initialize called");
		TapsellGameMaker.setGMListener(this);
        return TapsellGameMaker.initialize(RunnerActivity.CurrentActivity,appKey)==true ? "true" : "false" ;
    }

    public String requestAd(String zoneId, String isCached)
    {
		if(zoneId!=null && zoneId.isEmpty())
		{
			zoneId=null;
		}
		Log.e("Tapsell","requestAd called");
        return TapsellGameMaker.requestAd(RunnerActivity.CurrentActivity,zoneId,"true".equalsIgnoreCase(isCached))==true ? "true" : "false" ;
    }
	
	public String requestBannerAd(String zoneId, String bannerType, String horizontalGravity, String verticalGravity)
    {
		if(zoneId==null || zoneId.isEmpty()){
			return "false";
		}
		
		if(bannerType == null || (bannerType != BANNER_250x250 && bannerType != BANNER_300x250 && bannerType != BANNER_320x100 && bannerType != BANNER_320x50)){
			bannerType = BANNER_320x50;
		}
		
		if(horizontalGravity == null || (horizontalGravity != TOP && horizontalGravity != BOTTOM)){
			horizontalGravity = BOTTOM;
		}
		
		if(verticalGravity == null || (verticalGravity != RIGHT && verticalGravity != CENTER && verticalGravity != LEFT)){
			verticalGravity = CENTER;
		}
		
		Log.e("Tapsell","requestBannerAd called");
        return TapsellGameMaker.requestBannerAd(RunnerActivity.CurrentActivity,zoneId,Integer.parseInt(bannerType),Integer.parseInt(horizontalGravity),Integer.parseInt(verticalGravity))==true ? "true" : "false" ;
    }

    public final String isAdReadyToShow(String zoneId)
    {
		if(zoneId!=null && zoneId.isEmpty())
		{
			zoneId=null;
		}
		Log.e("Tapsell","isAdReadyToShow called");
        return TapsellGameMaker.isAdReadyToShow(RunnerActivity.CurrentActivity,zoneId)==true ? "true" : "false" ;
    }

    public final String getVersion()
    {
		Log.e("Tapsell","getVersion called");
        return TapsellGameMaker.getVersion();
    }
	
	public static final int EVENT_OTHER_SOCIAL = 70;
	
	public static final String EVENT_TYPE_AD_SHOW_FINISHED = "TAPSELL_AD_SHOW_FINISHED";
	public static final String EVENT_TYPE_AD_AVAILABLE = "TAPSELL_AD_AVAILABLE";
	public static final String EVENT_TYPE_NO_AD_AVAILABLE = "TAPSELL_NO_AD_AVAILABLE";
	public static final String EVENT_TYPE_ERROR = "TAPSELL_ERROR";
	public static final String EVENT_TYPE_NO_NETWORK = "TAPSELL_NO_NETWORK";
	public static final String EVENT_TYPE_AD_EXPIRING = "TAPSELL_AD_EXPIRING";
	public static final String EVENT_TYPE_AD_OPENED = "TAPSELL_AD_OPENED";
	public static final String EVENT_TYPE_AD_CLOSED = "TAPSELL_AD_CLOSED";
	
	@Override
    public void onAdShowFinished(String zoneId, String adId, boolean completed, boolean rewarded) {
		if(zoneId==null)
		{
			zoneId="";
		}
		Log.e("Tapsell","onAdShowFinished called, completed: "+completed+", rewarded: "+rewarded);
        int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
		RunnerJNILib.DsMapAddString( dsMapIndex, "type", EVENT_TYPE_AD_SHOW_FINISHED );
		RunnerJNILib.DsMapAddString( dsMapIndex, "zoneId", zoneId);
		RunnerJNILib.DsMapAddString( dsMapIndex, "adId", adId);
		RunnerJNILib.DsMapAddString( dsMapIndex, "completed", completed? "true" : "false");
		RunnerJNILib.DsMapAddString( dsMapIndex, "rewarded", rewarded? "true" : "false");
		RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
    }

    @Override
    public void onAdAvailable(String zoneId, String adId) {
		if(zoneId==null)
		{
			zoneId="";
		}
		Log.e("Tapsell","onAdAvailable called");
		int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
		RunnerJNILib.DsMapAddString( dsMapIndex, "type", EVENT_TYPE_AD_AVAILABLE );
		RunnerJNILib.DsMapAddString( dsMapIndex, "zoneId", zoneId);
		RunnerJNILib.DsMapAddString( dsMapIndex, "adId", adId);
		RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
    }

    @Override
    public void onError(String zoneId, String error) {
		if(zoneId==null)
		{
			zoneId="";
		}
		Log.e("Tapsell","onError called "+error);
		if(error==null)
		{
			error = "";
		}
		int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
		RunnerJNILib.DsMapAddString( dsMapIndex, "type", EVENT_TYPE_ERROR );
		RunnerJNILib.DsMapAddString( dsMapIndex, "zoneId", zoneId);
		RunnerJNILib.DsMapAddString( dsMapIndex, "error", error);
		RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
    }

    @Override
    public void onNoAdAvailable(String zoneId) {
		if(zoneId==null)
		{
			zoneId="";
		}
		Log.e("Tapsell","onNoAdAvailable called");
		int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
		RunnerJNILib.DsMapAddString( dsMapIndex, "type", EVENT_TYPE_NO_AD_AVAILABLE );
		RunnerJNILib.DsMapAddString( dsMapIndex, "zoneId", zoneId);
		RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
    }

    @Override
    public void onNoNetwork(String zoneId) {
		if(zoneId==null)
		{
			zoneId="";
		}
		Log.e("Tapsell","onNoNetwork called");
		int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
		RunnerJNILib.DsMapAddString( dsMapIndex, "type", EVENT_TYPE_NO_NETWORK );
		RunnerJNILib.DsMapAddString( dsMapIndex, "zoneId", zoneId);
		RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
    }

    @Override
    public void onExpiring(String zoneId, String adId) {
		if(zoneId==null)
		{
			zoneId="";
		}
		Log.e("Tapsell","onExpiring called");
		int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
		RunnerJNILib.DsMapAddString( dsMapIndex, "type", EVENT_TYPE_AD_EXPIRING );
		RunnerJNILib.DsMapAddString( dsMapIndex, "zoneId", zoneId);
		RunnerJNILib.DsMapAddString( dsMapIndex, "adId", adId);
		RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
    }

	@Override
	public void onOpened(String zoneId, String adId) {
		if(zoneId==null)
		{
			zoneId="";
		}
		Log.e("Tapsell","onOpened called");
		int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
		RunnerJNILib.DsMapAddString( dsMapIndex, "type", EVENT_TYPE_AD_OPENED );
		RunnerJNILib.DsMapAddString( dsMapIndex, "zoneId", zoneId);
		RunnerJNILib.DsMapAddString( dsMapIndex, "adId", adId);
		RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
	}

	@Override
	public void onClosed(String zoneId, String adId) {
		if(zoneId==null)
		{
			zoneId="";
		}
		Log.e("Tapsell","onClosed called");
		int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
		RunnerJNILib.DsMapAddString( dsMapIndex, "type", EVENT_TYPE_AD_CLOSED );
		RunnerJNILib.DsMapAddString( dsMapIndex, "zoneId", zoneId);
		RunnerJNILib.DsMapAddString( dsMapIndex, "adId", adId);
		RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
	}
	
}
