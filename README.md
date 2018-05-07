<div dir="rtl">
<a href="#part1">پیاده‌سازی تبلیغات ویدئویی (Interstitial/Rewarded Video) و بنری تمام صفحه (Interstitial Banner) در در پروژه GameMaker Studio</a></br>
  <a href="#part2">پیاده‌سازی تبلیغات بنری استاندارد در پروژه GameMaker Studio</a></br>
  <a href="#part3">موارد پیشرفته‌تر در SDK</a></br>
</div>
</br></br>

<div dir="rtl" id="part1">
  <h1>پیاده‌سازی تبلیغات ویدئویی (Interstitial/Rewarded Video) و بنری تمام صفحه (Interstitial Banner) در در پروژه GameMaker Studio</h1>
  &nbsp;
<h3>گام ۱: دریافت Extension</h3>
ابتدا Extension تپسل را از آدرس زیر دانلود کنید.
<p style="text-align: center;"><a href="https://storage.backtory.com/tapsell-server/Tapsell-Gamemaker-a3.1.0-i3.0.5.gmez"><button>دریافت فایل</button></a></p>
&nbsp;
<h3>گام ۲: اضافه کردن پلاگین تپسل به پروژه‌ی شما</h3>
در نرم افزار GameMaker، روی پوشه Extensions کلیک راست کرده و گزینه Import Extension را انتخاب کنید. سپس فایلی که در مرحله 1 دریافت نمودید را انتخاب کنید و به پروژه اضافه کنید.

&nbsp;
<h3>گام ۳: دریافت کلید تپسل</h3>
ابتدا وارد پنل مدیریت تپسل شده و با تعریف یک اپلیکیشن جدید با عنوان پکیج اپلیکیشن اندرویدی خود، یک کلید تپسل دریافت کنید.

از تب‌های بالای صفحه، «توسعه دهنده» را انتخاب نموده و سپس به بخش «اپ‌های من» بروید. یک اپلیکیشن جدید با عنوان پکیج اپلیکیشن اندرویدی خود تعریف نمایید. پس از ایجاد اپلیکیشن جدید، کلید تپسل برای آن اپلیکیشن تولید می‌شود و می‌توانید در صفحه «اپ‌های من» در طر مربوط به اپلیکیشن خود، با کلیک روی عبارت «نمایش» در ستون «کلید تپسل»، کلید اپلیکیشن خود را دریافت کنید.

&nbsp;
<h3>گام ۴: شروع کار با SDK تپسل</h3>
با ثبت برنامه خود در پنل تپسل، به شما یک عبارت به عنوان کلید برنامه داده خواهد شد. برای استفاده از تپسل ابتدا باید این کلید را به برنامه خود معرفی نمایید. نحوه‌ی معرفی کلید با تابع زیر است.
<pre dir="ltr"><span style="color: #ff9900;">tapsell_initialize</span>(<span style="color: #800000;">appKey</span>);</pre>
ورودی appKey کلید تپسلی است که در گام قبل از پنل تپسل دریافت کردید.

&nbsp;
<h3>گام ۵: دریافت تبلیغ</h3>
دریافت یک ویدئوی تبلیغاتی ممکن است زمانبر باشد (برحسب سرعت اینترنت کاربران). برای اینکه کاربر منتظر نماند، SDK تپسل ابتدا ویدئوی تبلیغاتی را دریافت می‌کند. همچنین در تپسل، تبلیغ می تواند در ناحیه‌های مختلفی از برنامه شما (مانند فروشگاه، انتهای هر مرحله، ابتدای مرحله جهت دریافت امتیاز دوبرابر، دریافت بنزین/لایف و ...) پخش شود. در تپسل به این ناحیه‌ها zone گفته می شود. ناحیه‌های هر اپلیکیشن در پنل تپسل تعریف می شوند.

با اجرای تابع زیر، می توانید یک درخواست تبلیغ به تپسل ارسال کرده و یک تبلیغ دریافت نمایید:
<pre dir="ltr"><span style="color: #000000;"><span style="color: #ff9900;">tapsell_requestAd</span>(<span style="color: #800000;">zoneId</span>)
</span></pre>
ورودی zoneId، کلید مربوط به محل zone نمایش تبلیغ است. جهت استفاده از zone پیش‌فرض، از یک رشته خالی بصورت زیر استفاده کنید.
<pre dir="ltr"><span style="color: #000000;"><span style="color: #ff9900;">tapsell_requestAd</span>(<span style="color: #800000;">""</span>)
</span></pre>
&nbsp;
<h3>گام ۶: دریافت پاسخ درخواست</h3>
پاسخ به درخواست‌ها در رویداد Asynchronous Social Event قابل مشاهده است. در این پاسخ رویداد یک ds_map به async_load اختصاص داده می‌شود . در ds_map برگردانده شده، کلید type نشان دهنده‌ی پاسخ برگردانده شده است.
<pre dir="ltr"><span style="color: #000000;">type = <span style="color: #ff9900;">string</span>(<span style="color: #800000;">async_load</span>[? <span style="color: #800000;">"type"</span>]);
</span></pre>
مقادیر مختلف ممکن برای این متغیر، یکی از موارد مندرج در در جدول ۱ می‌باشد.

&nbsp;
<table style="text-align: center; border-collapse: collapse;" width="100%"><caption>جدول ۱ مقادیر برگردانده شده برای متغیر type در درخواست تبلیغ و توضیحات مربوطه</caption>
<tbody>
<tr style="border-bottom: 1px solid #ddd;">
<th width="40%">مقدار</th>
<th width="60%">توضیحات</th>
</tr>
<tr style="background-color: #fefefe;">
<td dir="ltr" width="40%">TAPSELL_EVENT_AD_AVAILABLE</td>
<td>یک تبلیغ از سرور دریافت شده است. در این صورت، id تبلیغ با کلید adId در ds_map برگردانده می‌شود.</td>
</tr>
<tr style="background-color: #f2f2f2;">
<td dir="ltr" width="40%">TAPSELL_EVENT_NO_AD_AVAILABLE</td>
<td width="60%">تبلیغی برای zone انتخاب شده جهت نمایش موجود نمی‌باشد.</td>
</tr>
<tr style="background-color: #fefefe;">
<td dir="ltr" width="40%">TAPSELL_EVENT_ERROR</td>
<td width="60%">دریافت تبلیغ با خطا مواجه شده است.</td>
</tr>
<tr style="background-color: #f2f2f2;">
<td dir="ltr" width="40%">TAPSELL_EVENT_NO_NETWORK</td>
<td width="60%">اتصال به اینترنت برقرار نمی‌باشد.</td>
</tr>
<tr style="background-color: #fefefe;">
<td dir="ltr" width="40%">TAPSELL_EVENT_AD_EXPIRING</td>
<td width="60%">پس از گذشت زمانی از دریافت تبلیغ، تبلیغ منقضی می‌شود. در این حالت، تبلیغ داده شده قابل نمایش نخواهد بود و لازمست تبلیغ جدیدی دریافت نمایید.</td>
</tr>
</tbody>
</table>
&nbsp;
<h3>گام ۷: نمایش تبلیغ</h3>
جهت نمایش تبلیغ، نیاز به id یک تبلیغ دارید که پس از فراخوانی tapsell_requestAd دریافت می‌کنید. جهت نمایش تبلیغ، از تابع زیر استفاده نمایید. (این تابع حداکثر یک بار برای هر تبلیغ قابل اجراست):
<pre dir="ltr"><span style="color: #ff9900;">tapsell_showAd</span>(adId,disable_back,immersive_mode,rotation_mode,show_dialog)
</pre>
ورودی adId، آی‌دی تبلیغ می‌باشد. ورودی‌های back_disabled و immersive_mode و show_dialog به ترتیب جهت جلوگیری از back زدن کاربر هنگام نمایش ویدئو، نمایش ویدئو در حالت Immersive Mode و نمایش دیالوگ اخطار زمان back زدن کاربر در هنگام پخش تبلیغ ویدئویی جایزه‌دار استفاده می‌شوند. لازم به ذکر است که حالت immersive_mode فقط در اندروید استفاده می شود. این پارامترها باید بصورت string و با مقادیر “true” یا “false” به عنوان ورودی داده شوند. ورودی rotation_mode نیز می‌تواند یکی از مقادیر جدول ۲ باشد.

&nbsp;
<table style="text-align: center; border-collapse: collapse;" width="100%" cellpadding="6"><caption>جدول ۲ مقادیر قابل قبول برای ورودی rotation_mode</caption>
<tbody>
<tr style="border-bottom: 1px solid #ddd;">
<th width="40%">متغیر (نوع)</th>
<th width="60%">توضیحات</th>
</tr>
<tr style="background-color: #fefefe;">
<td dir="ltr" width="40%">TAPSELL_ORIENTATION_LOCKED_PORTRAIT</td>
<td width="60%">
<div style="text-align: center;" align="right">نمایش ویدئو در حالت Portrait</div></td>
</tr>
<tr style="background-color: #f2f2f2;">
<td style="direction: ltr; text-align: center;" width="40%">TAPSELL_ORIENTATION_LOCKED_LANDSCAPE</td>
<td width="60%">
<div style="text-align: center;" align="right">نمایش ویدئو در حالت Landscape</div></td>
</tr>
<tr style="background-color: #fefefe;">
<td style="direction: ltr; text-align: center;" width="40%">TAPSELL_ORIENTATION_UNLOCKED</td>
<td width="60%">
<div style="text-align: center;" align="right">عدم قفل کردن چرخش گوشی</div></td>
</tr>
<tr style="background-color: #f2f2f2;">
<td dir="ltr" width="40%">TAPSELL_ORIENTATION_LOCKED_REVERSED_PORTRAIT</td>
<td width="60%">
<div style="text-align: center;" align="right">نمایش ویدئو در حالت Reversed Portrait</div></td>
</tr>
<tr style="background-color: #fefefe;">
<td dir="ltr" width="40%">TAPSELL_ORIENTATION_LOCKED_REVERSED_LANDSCAPE</td>
<td width="60%">
<div style="text-align: center;" align="right">نمایش ویدئو در حالت Reversed Landscape</div></td>
</tr>
</tbody>
</table>
&nbsp;
<h3>گام ۸: دریافت نتیجه نمایش تبلیغ</h3>
پس از نمایش ویدئو به کاربر، یک Asynchronous Social Event جهت ارائه نتیجه نمایش بازگردانده می‌شود که مقدار “type” آن برابر TAPSELL_EVENT_AD_SHOW_FINISHED می‌باشد. در ds_map برگردانده شده، کلید‌های زیر موجود هستند.

&nbsp;
<table style="text-align: center; border-collapse: collapse;" width="100%" cellpadding="6"><caption>جدول ۳ کلیدهای موجود در پاسخ برگردانده شده جهت دریافت نتیجه نمایش</caption>
<tbody>
<tr style="border-bottom: 1px solid #ddd;">
<th width="40%">کلید</th>
<th width="60%">توضیحات</th>
</tr>
<tr style="background-color: #fefefe;">
<td dir="ltr" width="40%">"adId"</td>
<td width="60%">
<div style="text-align: center;" align="right">شناسه تبلیغ نشان داده شده</div></td>
</tr>
<tr style="background-color: #f2f2f2;">
<td style="direction: ltr; text-align: center;" width="40%">"zoneId"</td>
<td width="60%">
<div style="text-align: center;" align="right">شناسه مربوط به ناحیه نمایش تبلیغ</div></td>
</tr>
<tr style="background-color: #fefefe;">
<td style="direction: ltr; text-align: center;" width="40%">"completed"</td>
<td width="60%">
<div style="text-align: center;" align="right">یکی از مقادیر "true" و "false" (بصورت رشته) مبتنی بر اینکه آیا تبلیغ تا انتها دیده شده است یا خیر.</div></td>
</tr>
<tr style="background-color: #f2f2f2;">
<td style="direction: ltr; text-align: center;" width="40%">"rewarded"</td>
<td width="60%">
<div style="text-align: center;" align="right">یکی از مقادیر "true" و "false" (بصورت رشته) مبتنی بر اینکه آیا تبلیغ دارای جایزه بوده است یا خیر.</div></td>
</tr>
</tbody>
</table>
&nbsp;

در صورتی که مقادیر مربوط به کلید‌های completed و rewarded هردو برابر “true” باشند، مشخص می‌گردد که کاربر یک ویدئو در یک zone جایزه‌دار تبلیغ مشاهده کرده‌است و می‌توانید جایزه درون برنامه (سکه، اعتبار، بنزین یا ...) را به وی بدهید.

&nbsp;

&nbsp;
</div>

<div dir="rtl" id="part2">
  <h1>پیاده‌سازی تبلیغات بنری استاندارد در پروژه GameMaker Studio</h1>
  <h3>گام ۱: دریافت Extension</h3>
ابتدا Extension تپسل را از آدرس زیر دانلود کنید.
<p style="text-align: center;"><a href="https://storage.backtory.com/tapsell-server/Tapsell-Gamemaker-a3.1.0-i3.0.5.gmez"><button>دریافت فایل</button></a></p>
&nbsp;
<h3>گام ۲: اضافه کردن پلاگین تپسل به پروژه‌ی شما</h3>
در نرم افزار GameMaker، روی پوشه Extensions کلیک راست کرده و گزینه Import Extension را انتخاب کنید. سپس فایلی که در مرحله 1 دریافت نمودید را انتخاب کنید و به پروژه اضافه کنید.

&nbsp;
<h3>گام ۳: دریافت کلید تپسل</h3>
ابتدا وارد پنل مدیریت تپسل شده و با تعریف یک اپلیکیشن جدید با عنوان پکیج اپلیکیشن اندرویدی خود، یک کلید تپسل دریافت کنید.

از تب‌های بالای صفحه، «توسعه دهنده» را انتخاب نموده و سپس به بخش «اپ‌های من» بروید. یک اپلیکیشن جدید با عنوان پکیج اپلیکیشن اندرویدی خود تعریف نمایید. پس از ایجاد اپلیکیشن جدید، کلید تپسل برای آن اپلیکیشن تولید می‌شود و می‌توانید در صفحه «اپ‌های من» در طر مربوط به اپلیکیشن خود، با کلیک روی عبارت «نمایش» در ستون «کلید تپسل»، کلید اپلیکیشن خود را دریافت کنید.

&nbsp;
<h3>گام ۴: شروع کار با SDK تپسل</h3>
با ثبت برنامه خود در پنل تپسل، به شما یک عبارت به عنوان کلید برنامه داده خواهد شد. برای استفاده از تپسل ابتدا باید این کلید را به برنامه خود معرفی نمایید. نحوه‌ی معرفی کلید با تابع زیر است.
<pre dir="ltr"><span style="color: #ff9900;">tapsell_initialize</span>(<span style="color: #800000;">appKey</span>);</pre>
ورودی appKey کلید تپسلی است که در گام قبل از پنل تپسل دریافت کردید.

&nbsp;
<h3>گام ۵: دریافت تبلیغ</h3>
جهت نمایش بنر استاندارد، باید محلی برای نمایش آن در صفحه در نظر بگیرید. بنر استاندارد، دارای سایزهای استانداردی است که در SDK تپسل مشخص شده اند. جهت نمایش بنر، از تابع زیر استفاده کنید:
<pre dir="ltr">tapsell_requestBannerAd(zoneId, bannerType, horizontalGravity, verticalGravity)</pre>
مقدار zoneId کلیدی ست که بعد از ساخت اپلیکیشن در پنل و ثبت یک zone از نوع بنری استاندارد دریافت میکنید.ورودی ندازه های مختلف را بیان میکند و شامل مقادیر BANNER_320x100 , BANNER_320x50, BANNER_300x250 , BANNER_250x250 میباشد. ورودی horizontalGravity نشان میدهد که آیا تبلیغ، بالا یا پایین صفحه باشد و شامل TOP, BUTTOM می باشد، همچنین verticalGravity بیان میکند که تبلیغ از جهت عرضی در کجای صفحه باشد و میتواند شامل مقادیر LEFT, RIGHT, CENTER باشد. یک نمونه پیاده سازی کد به شکل زیر است:
<pre dir="ltr">tapsell_requestBannerAd("5a0ab0eb73916d0001696b68",BANNER_320x50,TOP,LEFT)</pre>
&nbsp;
<div id="s3gt_translate_tooltip_mini" class="s3gt_translate_tooltip_mini_box" style="background: initial !important; border: initial !important; border-radius: initial !important; border-spacing: initial !important; border-collapse: initial !important; direction: ltr !important; flex-direction: initial !important; font-weight: initial !important; height: initial !important; letter-spacing: initial !important; min-width: initial !important; max-width: initial !important; min-height: initial !important; max-height: initial !important; margin: auto !important; outline: initial !important; padding: initial !important; position: absolute; table-layout: initial !important; text-align: initial !important; text-shadow: initial !important; width: initial !important; word-break: initial !important; word-spacing: initial !important; overflow-wrap: initial !important; box-sizing: initial !important; display: initial !important; color: inherit !important; font-size: 13px !important; font-family: X-LocaleSpecific,sans-serif,Tahoma,Helvetica !important; line-height: 13px !important; vertical-align: top !important; white-space: inherit !important; left: 486px; top: 270px;"></div>
<div id="s3gt_translate_tooltip_mini" class="s3gt_translate_tooltip_mini_box" style="background: initial !important; border: initial !important; border-radius: initial !important; border-spacing: initial !important; border-collapse: initial !important; direction: ltr !important; flex-direction: initial !important; font-weight: initial !important; height: initial !important; letter-spacing: initial !important; min-width: initial !important; max-width: initial !important; min-height: initial !important; max-height: initial !important; margin: auto !important; outline: initial !important; padding: initial !important; position: absolute; table-layout: initial !important; text-align: initial !important; text-shadow: initial !important; width: initial !important; word-break: initial !important; word-spacing: initial !important; overflow-wrap: initial !important; box-sizing: initial !important; display: initial !important; color: inherit !important; font-size: 13px !important; font-family: X-LocaleSpecific, sans-serif, Tahoma, Helvetica !important; line-height: 13px !important; vertical-align: top !important; white-space: inherit !important; left: 715px; top: 1511px; opacity: 0.6;">
<div id="s3gt_translate_tooltip_mini_logo" class="s3gt_translate_tooltip_mini" title="Translate selected text"></div>
<div id="s3gt_translate_tooltip_mini_sound" class="s3gt_translate_tooltip_mini" title="Play"></div>
<div id="s3gt_translate_tooltip_mini_copy" class="s3gt_translate_tooltip_mini" title="Copy text to Clipboard"></div>
</div>
</div>

<div dir="rtl" id="part3">
  <h1>موارد پیشرفته‌تر در SDK</h1>
  <h3>بررسی وجود تبلیغ دریافت شده</h3>
در صورتیکه در SDK برای یک ناحیه درخواست دریافت تبلیغ از سرور انجام داده باشید، علاوه بر <code>callback</code> داده شده در تابع <code>requestAd</code>، می‌توانید از تابع زیر نیز برای چک کردن وجود تبلیغ دریافت شده استفاده کنید.
<pre style="direction: ltr; margin: 0; line-height: 125%;"><span style="color: #ff9900;">tapsell_isAdReadyToShow</span><span style="color: #333333;">(<span style="color: #800000;">zoneId</span></span><span style="color: #333333;">);</span></pre>
ورودی <code>zoneId</code> در این تابع، شناسه ناحیه نمایش تبلیغ است که از پنل تپسل دریافت نموده‌اید.

&nbsp;
<h3>دریافت نسخه SDK تپسل</h3>
درصورتی که نیازمند به دانستن نسخه تپسل پیاده‌سازی شده در اپلیکیشن خود هستید، می‌توانید با فراخوانی تابع زیر عنوان نسخه را دریافت نمایید.
<pre style="direction: ltr; margin: 0; line-height: 125%;"><span style="color: #ff9900;">tapsell_getVersion</span><span style="color: #333333;">();</span></pre>
<h3></h3>
<h3>تنظیمات کشینگ</h3>
همانطور که در بخش <a href="https://answers.tapsell.ir/?ht_kb=android-sdk-p2">پیاده‌سازی SDK تپسل در اپلیکیشن</a> گفته شد، از نسخه ۳ به بعد تپسل قابلیت نمایش ویدئو بصورت استریم و همینطور نمایش ویدئو بعد از دانلود فایل (کشینگ) را دارد. با این قابلیت، قبل از نمایش تبلیغ و در هنگامی که کاربر مشغول استفاده از اپلیکیشن است، ویدئو بطور کامل دریافت می‌شود و کاربر بدون هیچگونه مکثی می‌تواند ویدئو را تماشا کند.

از طرف دیگر، در اپلیکیشن‌ها و بازی‌های آنلاین، دریافت ویدئو در پس زمینه ممکن است در روند اصلی برنامه خلل ایجاد کند و آن را کند نماید.

جهت جلوگیری از اشغال پهنای باند زیاد توسط تپسل، شما می‌توانید درصد مشخصی از کل پهنای باند موجود را به دانلود ویدئو اختصاص دهید. جهت انجام این عمل، تابع زیر را قبل از درخواست تبلیغ، فراخوانی کنید.
<pre style="direction: ltr; margin: 0; line-height: 125%;"><span style="color: #ff9900;">tapsell_setMaxAllowedBandwidthUsagePercentage</span><span style="color: #333333;">(</span><span style="color: #800000;">maxPercentage</span><span style="color: #333333;">);</span></pre>
در این تابع، ورودی <code>maxPercentage</code> یک رشته حاوی عدد و مشخص کننده حداکثر درصدی از پهنای باند در دسترس اپلیکیشن است که SDK تپسل از آن برای دریافت ویدئو استفاده می‌کند. این پارامتر باید بین "10" تا "100" باشد.

همچنین درصورتی که از سرعت دانلود واقعی کاربر در اپلیکیشن خود اطلاع دارید می‌توانید به کمک تابع زیر، مقدار حداکثر پهنای باند قابل استفاده برای دانلود ویدئو را به کمک تابع زیر تنظیم کنید.
<pre style="direction: ltr; margin: 0; line-height: 125%;"><span style="color: #ff9900;">tapsell_setMaxAllowedBandwidthUsage</span><span style="color: #333333;">(<span style="color: #800000;">m</span></span><span style="color: #800000;">axBpsSpeed</span><span style="color: #333333;">);</span></pre>
ورودی دوم این تابع، میزان حداکثر سرعت دانلود ویدئو است که باید به واحد بایت بر ثانیه داده شود.

در صورتی که در بخشی از اپلیکیشن خود می‌خواهید تنظیمات مربوط به محدودیت سرعت دانلود را غیرفعال نمایید، از تابع زیر استفاده کنید.
<pre style="direction: ltr; margin: 0; line-height: 125%;"><span style="color: #ff9900;">tapsell_clearBandwidthUsageConstrains</span><span style="color: #333333;">(</span><span style="color: #333333;">);</span></pre>
&nbsp;

توضیحات بیشتر درباره کشینگ و استریمینگ در SDK تپسل را <a href="https://answers.tapsell.ir/?ht_kb=cached-vs-streamed">اینجا</a> بخوانید.

&nbsp;
<h3>تنظیمات دسترسی‌های زمان اجرا (Run Time Permissions)</h3>
از نسخه اندروید 6 و بالاتر، برخی دسترسی‌ها در اندروید در زمان اجرا باید از کاربر درخواست شوند. یکی از این دسترسی‌ها، دسترسی <code>READ_PHONE_STATE</code> است که توسط تپسل استفاده می‌شود و بدون این دسترسی، SDK تپسل قابل استفاده نیست. برای سهولت پیاده‌سازی، SDK تپسل بصورت اتوماتیک دسترسی‌ها را از کاربر درخواست می‌کند و هربار درخواست تبلیغی ارسال شود، درصورتی که دسترسی مورد نیاز موجود نباشد، این دسترسی از کاربر خواسته می شود.

در صورتی که شما می‌خواهید درخواست دسترسی‌ها از کاربر را به نحو دیگری در اپلیکیشن خود پیاده‌سازی نمایید، می‌توانید این ویژگی پیش‌فرض را در تپسل غیر فعال کنید. جهت انجام این عمل، کافیست از تابع زیر استفاده کنید.
<pre style="direction: ltr; margin: 0; line-height: 125%;"><span style="color: #ff9900;">tapsell_setAutoHandlePermissions</span><span style="color: #333333;">(</span><span style="color: #800000;">"false"</span><span style="color: #333333;">)</span></pre>
با این دستور، درخواست دسترسی توسط SDK تپسل به کاربر نشان داده نمی‌شود و می‌توانید بصورت مطلوب خود آن را پیاده‌سازی نمایید.

&nbsp;
<h3>حالت دیباگ (Debug Mode)</h3>
در هنگام پیاده‌سازی SDK، ممکن است بدلیل عدم رعایت نکات گفته شده و یا خطاهای دیگر، تبلیغات قابل دریافت و نمایش نباشند. حالت دیباگ جهت تسهیل فرآیند عیب‌یابی در هنگام پیاده‌سازی تعبیه شده است. با فعالسازی این حالت، می‌توانید گزارش‌های لاگ نمایش داده شده توسط SDK را در logcat مشاهده کنید. برای فعالسازی حالت دیباگ کافیست از تابع زیر استفاده کنید.
<pre style="direction: ltr; margin: 0; line-height: 125%;">TapsellConfiguration config <span style="color: #333333;">=</span> <span style="color: #008800; font-weight: bold;">new</span> TapsellConfiguration<span style="color: #333333;">();</span>
<span style="color: #ff9900;">tapsell_setDebugMode</span><span style="color: #333333;">(</span><span style="color: #800000;">"true"</span><span style="color: #333333;">);</span>
Tapsell<span style="color: #333333;">.</span><span style="color: #0000cc;">initialize</span><span style="color: #333333;">(</span>context<span style="color: #333333;">,</span> config<span style="color: #333333;">,</span> appKey<span style="color: #333333;">);</span>
</pre>
سپس در نرم‌افزار AndroidStudio از بخش Android Monitor، قسمت logcat را باز کرده و لاگ‌های نوشته شده را بررسی کنید.
<p style="text-align: center;"><img src="https://answers.tapsell.ir/wp-content/uploads/2017/02/logs.png" /></p>

<div id="s3gt_translate_tooltip_mini" class="s3gt_translate_tooltip_mini_box" style="background: initial !important; border: initial !important; border-radius: initial !important; border-spacing: initial !important; border-collapse: initial !important; direction: ltr !important; flex-direction: initial !important; font-weight: initial !important; height: initial !important; letter-spacing: initial !important; min-width: initial !important; max-width: initial !important; min-height: initial !important; max-height: initial !important; margin: auto !important; outline: initial !important; padding: initial !important; position: absolute; table-layout: initial !important; text-align: initial !important; text-shadow: initial !important; width: initial !important; word-break: initial !important; word-spacing: initial !important; overflow-wrap: initial !important; box-sizing: initial !important; display: initial !important; color: inherit !important; font-size: 13px !important; font-family: X-LocaleSpecific,sans-serif,Tahoma,Helvetica !important; line-height: 13px !important; vertical-align: top !important; white-space: inherit !important; left: 553px; top: 3092px; opacity: 0.55;"></div>
<div id="s3gt_translate_tooltip_mini" class="s3gt_translate_tooltip_mini_box" style="background: initial !important; border: initial !important; border-radius: initial !important; border-spacing: initial !important; border-collapse: initial !important; direction: ltr !important; flex-direction: initial !important; font-weight: initial !important; height: initial !important; letter-spacing: initial !important; min-width: initial !important; max-width: initial !important; min-height: initial !important; max-height: initial !important; margin: auto !important; outline: initial !important; padding: initial !important; position: absolute; table-layout: initial !important; text-align: initial !important; text-shadow: initial !important; width: initial !important; word-break: initial !important; word-spacing: initial !important; overflow-wrap: initial !important; box-sizing: initial !important; display: initial !important; color: inherit !important; font-size: 13px !important; font-family: X-LocaleSpecific, sans-serif, Tahoma, Helvetica !important; line-height: 13px !important; vertical-align: top !important; white-space: inherit !important; left: 546px; top: 2623px; opacity: 0.55;">
<div id="s3gt_translate_tooltip_mini_logo" class="s3gt_translate_tooltip_mini" title="Translate selected text"></div>
<div id="s3gt_translate_tooltip_mini_sound" class="s3gt_translate_tooltip_mini" title="Play"></div>
<div id="s3gt_translate_tooltip_mini_copy" class="s3gt_translate_tooltip_mini" title="Copy text to Clipboard"></div>
</div>
</div>
