@interface TapsellGMController : NSObject

- (NSString *) initialize:(NSString* )arg0;
- (NSString *) setAppUserId:(NSString* )arg0;
- (NSString *)getAppUserId;
- (NSString *) setDebugMode:(NSString*)arg0;
- (NSString *) isDebugMode;
- (NSString *) requestAd:(NSString* )arg0 Arg2:(NSString*)arg1;
- (NSString *) getVersion;
- (NSString *) showAd:(NSString*)arg0 Arg2:(NSString*)arg1 Arg3:(NSString*)arg2 Arg4:(NSString*)arg3 Arg5:(NSString*)arg4;

- (NSString *) setAutoHandlePermissions:(NSString* )arg0;
- (NSString *) setMaxAllowedBandwidthUsage:(NSString* )arg0;
- (NSString *) setMaxAllowedBandwidthUsagePercentage:(NSString* )arg0;
- (NSString *) clearBandwidthUsageConstrains;

@end
