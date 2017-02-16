package ${YYAndroidPackageName};

import android.util.Log;

import ir.tapsell.sdk.TapsellExtraPlatforms;
import ir.tapsell.sdk.TapsellGameMaker;
import ir.tapsell.sdk.TapsellGameMakerListener;

import ${YYAndroidPackageName}.R;
import com.yoyogames.runner.RunnerJNILib;
import ${YYAndroidPackageName}.RunnerActivity;

/**
 * Created by Sina Rezaei (sinarezaei1991@gmail.com) on 1/8/2017.
 */
public class Tapsell implements TapsellGameMakerListener{

	public String showAd(String id, String back_disabled, String immersive_mode, String rotation_mode, String show_dialog)
    {
		Log.e("Tapsell","showAd called");
        TapsellExtraPlatforms.showAd(RunnerActivity.CurrentActivity,id,back_disabled.equalsIgnoreCase("true"),immersive_mode.equalsIgnoreCase("true"),Integer.parseInt(rotation_mode),show_dialog!=null && show_dialog.equalsIgnoreCase("true"));
		return "true";
    }

    public String setDebugMode(String debug)
    {
		Log.e("Tapsell","setDebugMode called");
        TapsellExtraPlatforms.setDebugMode(RunnerActivity.CurrentActivity,debug.equalsIgnoreCase("true"));
		return "Done";
    }
	
	public String setAutoHandlePermissions(String handle)
    {
        TapsellExtraPlatforms.setAutoHandlePermissions(RunnerActivity.CurrentActivity,handle!=null && handle.equalsIgnoreCase("true"));
		return "Done";
    }
	
	public String setMaxAllowedBandwidthUsage(String maxBpsSpeed)
    {
        TapsellExtraPlatforms.setMaxAllowedBandwidthUsage(RunnerActivity.CurrentActivity,Integer.parseInt(maxBpsSpeed));
		return "Done";
    }

    public String setMaxAllowedBandwidthUsagePercentage(String maxPercentage)
    {
        TapsellExtraPlatforms.setMaxAllowedBandwidthUsagePercentage(RunnerActivity.CurrentActivity,Integer.parseInt("maxPercentage"));
		return "Done";
    }

    public String clearBandwidthUsageConstrains()
    {
        TapsellExtraPlatforms.clearBandwidthUsageConstrains(RunnerActivity.CurrentActivity);
		return "Done";
    }

    public String isDebugMode()
    {
		Log.e("Tapsell","isDebugMode called");
        return TapsellExtraPlatforms.isDebugMode(RunnerActivity.CurrentActivity)==true ? "true" : "false";
    }

    public String setAppUserId(String appUserId)
    {
		Log.e("Tapsell","setAppUserId called");
        TapsellExtraPlatforms.setAppUserId(RunnerActivity.CurrentActivity,appUserId);
		return "Done";
    }

    public String getAppUserId()
    {
		Log.e("Tapsell","getAppUserId called");
        return TapsellExtraPlatforms.getAppUserId(RunnerActivity.CurrentActivity);
    }

    public String initialize( String appKey)
    {
		Log.e("Tapsell","initialize called");
		TapsellGameMaker.setGMListener(this);
        return TapsellExtraPlatforms.initialize(RunnerActivity.CurrentActivity,appKey)==true ? "true" : "false" ;
    }

    public String requestAd(String zoneId, String isCached)
    {
		if(zoneId!=null && zoneId.isEmpty())
		{
			zoneId=null;
		}
		Log.e("Tapsell","requestAd called");
        return TapsellExtraPlatforms.requestAd(RunnerActivity.CurrentActivity,zoneId,"true".equalsIgnoreCase(isCached))==true ? "true" : "false" ;
    }

    public final String isAdReadyToShow(String zoneId)
    {
		if(zoneId!=null && zoneId.isEmpty())
		{
			zoneId=null;
		}
		Log.e("Tapsell","isAdReadyToShow called");
        return TapsellExtraPlatforms.isAdReadyToShow(RunnerActivity.CurrentActivity,zoneId)==true ? "true" : "false" ;
    }

    public final String getVersion()
    {
		Log.e("Tapsell","getVersion called");
        return TapsellExtraPlatforms.getVersion();
    }
	
	public static final int EVENT_OTHER_SOCIAL = 70;
	
	public static final String EVENT_TYPE_AD_SHOW_FINISHED = "TAPSELL_AD_SHOW_FINISHED";
	public static final String EVENT_TYPE_AD_AVAILABLE = "TAPSELL_AD_AVAILABLE";
	public static final String EVENT_TYPE_NO_AD_AVAILABLE = "TAPSELL_NO_AD_AVAILABLE";
	public static final String EVENT_TYPE_ERROR = "TAPSELL_ERROR";
	public static final String EVENT_TYPE_NO_NETWORK = "TAPSELL_NO_NETWORK";
	public static final String EVENT_TYPE_AD_EXPIRING = "TAPSELL_AD_EXPIRING";
	
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
	
}
