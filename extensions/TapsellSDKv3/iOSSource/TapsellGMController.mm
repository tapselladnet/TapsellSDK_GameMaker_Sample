#import "TapsellGMController.h"

#import <TapsellSDKv3/TapsellSDKv3.h>
#include <asl.h>
#include <stdio.h>

// Converts NSString to C style string by way of copy (Mono will free it)
#define TS_MakeStringCopy( _x_ ) ( _x_ != NULL && [_x_ isKindOfClass:[NSString class]] ) ? strdup( [_x_ UTF8String] ) : NULL

@implementation TapsellGMController

const int EVENT_OTHER_SOCIAL = 70;
extern int CreateDsMap( int _num, ... );
extern void CreateAsynEventWithDSMap(int dsmapindex, int event_index);

-(instancetype) init
{
    self = [super init];
    if(self){
        [[TapsellExtraPlatformsController sharedInstance]
         setPlatformControllerOnAdAvailable:^(TapsellAd * _Nullable ad) {
             NSLog(@"TAPSELL_AD_AVAILABLE! , adId: %@",[ad getId]);
             int dsMapIndex = CreateDsMap(3,
                                          "type", 0.0, "TAPSELL_AD_AVAILABLE",
                                          "zoneId", 0.0, TS_MakeStringCopy(ad.zoneId),
                                          "adId", 0.0, TS_MakeStringCopy([ad getId])
                                          );
             CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
         } onNoAdAvailable:^(NSString * _Nullable zoneId) {
             int dsMapIndex = CreateDsMap(2,
                                          "type", 0.0, "TAPSELL_NO_AD_AVAILABLE",
                                          "zoneId", 0.0, TS_MakeStringCopy(zoneId)
                                          );
             CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
         } onError:^(NSString * _Nullable error, NSString * _Nullable zoneId) {
             int dsMapIndex = CreateDsMap(3,
                                          "type", 0.0, "TAPSELL_ERROR",
                                          "zoneId", 0.0, TS_MakeStringCopy(zoneId),
                                          "error", 0.0, TS_MakeStringCopy(error)
                                          );
             CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
         } onExpiring:^(TapsellAd * _Nullable ad) {
             int dsMapIndex = CreateDsMap(3,
                                          "type", 0.0, "TAPSELL_AD_EXPIRING",
                                          "zoneId", 0.0, TS_MakeStringCopy(ad.zoneId),
                                          "adId", 0.0, TS_MakeStringCopy([ad getId])
                                          );
             CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
         }];
        [TapsellExtraPlatformsController setAdShowFinishedCallback:^(TapsellAd *ad, BOOL completed) {
            int dsMapIndex = CreateDsMap(5,
                                         "type", 0.0, "TAPSELL_AD_SHOW_FINISHED",
                                         "zoneId", 0.0, TS_MakeStringCopy(ad.zoneId),
                                         "adId", 0.0, TS_MakeStringCopy([ad getId]),
                                         "completed", 0.0, completed? "true" : "false",
                                         "rewarded", 0.0, [ad isRewardedAd]? "true" : "false"
                                         );
            CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
        }];
    }
    return self;
}

- (NSString *) initialize:(NSString* )arg0{
    [TapsellExtraPlatformsController initializeWithAppKey:arg0];
	return @"true";
}

- (NSString *) setAppUserId:(NSString* )arg0{
    [TapsellExtraPlatformsController setAppUserId:arg0];
	return @"true";
}

- (NSString*)getAppUserId{
    return [TapsellExtraPlatformsController getAppUserId];
}

- (NSString*) isDebugMode{
    BOOL debugMode = [TapsellExtraPlatformsController isDebugMode];
    if(debugMode)
    {
        return @"true";
    }
    else
    {
        return @"false";
    }
}

- (NSString *) setDebugMode:(NSString*)arg0{
    BOOL debugModeEnabled = YES;
    if([@"true" isEqualToString:arg0]){
        debugModeEnabled = YES;
    }
    else{
        debugModeEnabled = NO;
    }
    [TapsellExtraPlatformsController setDebugMode:debugModeEnabled];
	return @"true";
}

- (NSString *) requestAd:(NSString* )arg0 Arg2:(NSString*)arg1{
    BOOL cached = YES;
    if([@"true" isEqualToString:arg1]){
        cached = YES;
    }
    else{
        cached = NO;
    }
    [TapsellExtraPlatformsController requestAdForZone:arg0 isCached:cached];
	return @"true";
}

- (NSString *) showAd:(NSString*)arg0 Arg2:(NSString*)arg1 Arg3:(NSString*)arg2 Arg4:(NSString*)arg3 Arg5:(NSString*)arg4{
    TSAdShowOptions* showOptions = [[TSAdShowOptions alloc] init];
    if([@"true" isEqualToString:arg1]){
        [showOptions setBackDisabled:YES];
    }
    else{
        [showOptions setBackDisabled:NO];
    }
    if([@"true" isEqualToString:arg4]){
        [showOptions setShowDialoge:YES];
    }
    else{
        [showOptions setShowDialoge:NO];
    }
    NSNumberFormatter *f = [[NSNumberFormatter alloc] init];
    f.numberStyle = NSNumberFormatterDecimalStyle;
    [showOptions setOrientationNumber:[f numberFromString:arg3]];
    [[TapsellExtraPlatformsController sharedInstance] showAd:arg0 withOptions:showOptions];
	return @"true";
}

- (NSString*) getVersion{
    return [TapsellExtraPlatformsController getVersion];
}

- (NSString *) setAutoHandlePermissions:(NSString* )arg0{
    return @"Not Supported on This Platform.";
}
- (NSString *) setMaxAllowedBandwidthUsage:(NSString* )arg0{
    return @"Not Supported on This Platform.";
}
- (NSString *) setMaxAllowedBandwidthUsagePercentage:(NSString* )arg0{
    return @"Not Supported on This Platform.";
}
- (NSString *) clearBandwidthUsageConstrains{
    return @"Not Supported on This Platform.";
}

@end
