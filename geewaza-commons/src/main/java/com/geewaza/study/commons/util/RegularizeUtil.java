package com.geewaza.study.commons.util;

import java.util.Hashtable;
import java.util.Map;

public class RegularizeUtil {

    private static final String simpleAndTiadition = "皑皚蔼藹霭靄爱愛嫒嬡碍礙暧曖庵菴谙諳暗闇暗晻翱翶翱翺袄襖媪媼奥奧坝垻坝壩罢罷霸覇摆擺败敗稗粺颁頒板闆办辦帮幫绑綁榜牓谤謗褒裦宝寶饱飽褓緥报報杯盃杯桮贝貝狈狽备備背揹悖誖惫憊辈輩奔逩奔犇绷綳绷繃逼偪笔筆币幣毕畢闭閉荜蓽毙斃筚篳辟闢弊獘边邊编編贬貶变變辩辯辫辮标標骠驃镖鏢飚飈表錶别別别彆宾賓宾賔傧儐滨濱缤繽槟檳濒瀕冰氷饼餅禀稟并並并竝拨撥剥剝钵缽钵鉢饽餑驳駁驳駮博愽鹁鵓卜蔔补補布佈财財采埰采寀采採彩綵睬倸参參参葠参蓡骖驂残殘蚕蠶惭慚惭慙惨慘黪黲灿燦仓倉伧傖沧滄苍蒼舱艙操撡艹艸册冊侧側厕厠厕廁恻惻测測策筞策筴层層插挿查査察詧诧詫钗釵侪儕掺摻搀攙婵嬋谗讒禅禪馋饞缠纏蝉蟬产産谄諂铲剷铲鏟阐闡忏懺颤顫伥倀鲳鯧长長肠腸苌萇尝嘗尝嚐偿償厂厰厂廠场塲场場怅悵畅暢钞鈔车車砗硨扯撦彻徹尘塵陈陳谌訦谌諶碜硶碜磣闯闖衬襯称稱龀齔趁趂铛鐺撑撐诚誠乘乗惩懲塍堘澄澂骋騁吃喫痴癡驰馳迟遲齿齒耻恥饬飭炽熾敕勅冲沖冲衝虫蟲宠寵铳銃俦儔绸綢畴疇筹籌酬詶酬酧酬醻踌躊丑醜瞅矁出齣刍芻厨廚锄耡锄鋤雏雛橱櫥蹰躕础礎储儲处処处處绌絀触觸传傳船舩囱囪疮瘡窗窓窗牎窗牕床牀创創怆愴捶搥棰箠锤錘锤鎚春旾纯純唇脣莼蒓莼蓴淳湻鹑鶉醇醕绰綽辍輟龊齪词詞辞辤辞辭鹚鶿鹚鷀糍餈赐賜从從匆怱匆悤苁蓯枞樅葱蔥骢驄聪聰丛樷丛叢凑湊粗觕粗麤蹴蹵撺攛镩鑹蹿躥窜竄篡簒脆脃村邨鹾鹺锉銼错錯达達沓遝鞑韃呆獃绐紿带帶玳蝳贷貸单單担擔郸鄲殚殫瘅癉箪簞胆膽掸撣诞誕啖啗啖噉弹彈惮憚当當当儅当噹裆襠挡擋挡攩党黨凼氹砀碭荡蕩荡盪档檔导導岛島捣搗捣擣祷禱焘燾盗盜锝鍀德悳灯燈邓鄧凳櫈镫鐙堤隄镝鏑敌敵涤滌诋詆抵牴抵觝递遞谛諦缔締蒂蔕颠顛巅巔癫癲点點电電垫墊钿鈿淀澱雕彫雕琱雕鵰鲷鯛吊弔钓釣调調铞銱谍諜喋啑叠曡叠疉叠疊蝶蜨鲽鰈钉釘顶頂订訂碇矴碇椗锭錠丢丟铥銩东東冬鼕岽崬岽崠鸫鶇动動冻凍峒峝栋棟胨腖兜兠斗鬥斗鬦斗鬭钭鈄豆荳窦竇读讀渎凟渎瀆椟匵椟櫝牍牘犊犢黩黷独獨笃篤赌賭睹覩妒妬镀鍍端耑断斷缎緞煅煆锻鍛簖籪队隊对對兑兌怼懟镦鐓吨噸墩墪趸躉炖燉钝鈍顿頓遁遯夺奪铎鐸朵朶垛垜缍綞堕墮跺跥讹訛讹譌峨峩锇鋨鹅鵝鹅鵞额額婀娿厄戹厄阨轭軛垩堊恶惡恶噁饿餓谔諤阏閼萼蕚腭齶锷鍔鹗鶚颚顎鳄鰐鳄鱷儿兒鸸鴯鲕鮞尔爾迩邇饵餌铒鉺贰貳发發发髮罚罰罚罸阀閥法灋珐琺帆颿翻飜翻繙凡凣矾礬钒釩烦煩繁緐泛氾泛汎饭飯范範贩販钫鈁鲂魴仿徬仿倣仿髣访訪纺紡飞飛绯緋鲱鯡诽誹废廢费費痱疿镄鐨纷紛氛雰坟墳奋奮偾僨愤憤粪糞鲼鱝丰豐风風沣灃枫楓疯瘋砜碸峰峯锋鋒冯馮缝縫讽諷凤鳳佛彿夫伕肤膚麸麩麸粰凫鳧绂紱绋紼辐輻幞襆呒嘸抚撫俯俛俯頫辅輔讣訃妇婦负負附坿驸駙复複复復赋賦缚縛鲋鮒赙賻鳆鰒钆釓嘎嘠该該赅賅丐匃丐匄钙鈣盖蓋概槩干幹干榦杆桿尴尲尴尷秆稈赶趕绀紺赣贛冈岡刚剛岗崗纲綱肛疘钢鋼杠槓戆戇皋臯槔橰糕餻缟縞稿槀镐鎬诰誥锆鋯纥紇胳肐鸽鴿搁擱歌謌阁閣镉鎘个個个箇铬鉻给給亘亙耕畊赓賡绠綆鲠骾鲠鯁宫宮躬躳龚龔巩鞏贡貢沟溝钩鈎钩鉤缑緱构搆构構诟詬购購够夠觏覯轱軲鸪鴣毂轂鹘鶻诂詁谷穀钴鈷蛊蠱鹄鵠鼓皷顾顧雇僱锢錮鲴鯝刮颳鸹鴰剐剮诖詿挂掛拐枴拐柺怪恠关関关關观觀鳏鰥馆舘馆館管琯管筦贯貫惯慣掼摜鹳鸛罐鑵广廣犷獷归歸妫媯妫嬀龟龜规規规槼闺閨瑰瓌鲑鮭轨軌匦匭诡詭刽劊刿劌柜櫃贵貴鳜鱖衮袞绲緄辊輥滚滾鲧鮌鲧鯀呙咼埚堝锅鍋蝈蟈国囯国國帼幗掴摑果菓椁槨过過铪鉿骇駭顸頇函圅韩韓汉漢悍猂焊釬焊銲颔頷绗絎颃頏蚝蠔嗥獋号號皓暠皓皜颢顥灏灝诃訶合閤合郃和咊阂閡核覈盍盇颌頜阖闔贺賀鹤鶴恒恆横橫轰轟哄鬨红紅闳閎荭葒鸿鴻黉黌讧訌糇餱鲎鱟呼虖呼嘑呼謼轷軤胡衚胡鬍壶壺鹕鶘糊餬浒滸户戶冱沍护護沪滬鹱鸌花芲花蘤华華哗嘩哗譁骅驊铧鏵划劃画畫话話桦樺怀懷坏壞欢懽欢歡獾貛还還环環锾鍰缳繯缓緩奂奐唤喚换換涣渙焕煥痪瘓鲩鯇黄黃鳇鰉恍怳谎謊诙詼咴噅挥揮晖暉珲琿辉煇辉輝徽幑回囘回囬回廻回迴蛔蚘蛔痐蛔蛕蛔蜖汇匯汇彙汇滙会會讳諱哕噦浍澮绘繪荟薈诲誨桧檜烩燴贿賄秽穢缋繢毁燬毁譭毁毀昏昬荤葷阍閽浑渾馄餛诨諢锪鍃钬鈥货貨获獲获穫祸禍镬鑊讥譏击擊叽嘰饥飢饥饑机機玑璣矶磯鸡雞鸡鷄迹跡迹蹟积積绩勣绩績缉緝赍賫赍齎跻躋齑齏羁羈级級极極楫檝辑輯几幾虮蟣挤擠计計记記纪紀际際剂劑哜嚌济濟继繼觊覬蓟薊霁霽鲚鱭鲫鯽骥驥夹夾夹裌浃浹家傢镓鎵郏郟荚莢铗鋏蛱蛺颊頰贾賈钾鉀价價驾駕戋戔奸姦坚堅歼殲间間艰艱监監笺牋笺箋缄緘缣縑鲣鰹鹣鶼鞯韉拣揀枧梘俭儉茧繭捡撿笕筧减減检檢睑瞼裥襇锏鐧简簡谫譾戬戩碱鹼碱堿见見饯餞剑劍剑劒荐薦贱賤涧澗舰艦渐漸谏諫溅濺践踐鉴鋻鉴鑑鉴鑒键鍵槛檻姜薑将將浆漿僵殭缰繮缰韁讲講奖獎桨槳蒋蔣绛絳酱醬娇嬌浇澆骄驕胶膠鲛鮫鹪鷦侥僥挢撟绞絞饺餃矫矯脚腳铰鉸搅攪剿勦缴繳叫呌峤嶠轿轎较較阶階阶堦疖癤秸稭节節讦訐劫刦劫刧劫刼杰傑诘詰洁潔结結颉頡鲒鮚届屆诫誡斤觔仅僅卺巹紧緊谨謹锦錦馑饉尽盡尽儘劲勁进進荩藎晋晉烬燼赆賮赆贐缙縉觐覲泾涇经經茎莖荆荊惊驚鲸鯨阱穽刭剄颈頸净淨弪弳径徑径逕胫脛痉痙竞競靓靚静靜镜鏡迥逈炯埛纠糾鸠鳩阄鬮揪揫韭韮旧舊厩廄厩廐救捄鹫鷲驹駒锔鋦局侷局跼举舉举擧榉櫸龃齟讵詎钜鉅剧劇惧懼据據飓颶锯鋸窭窶屦屨鹃鵑镌鎸镌鐫卷捲锩錈倦勌桊棬狷獧绢絹隽雋眷睠决決诀訣珏玨绝絕觉覺谲譎橛橜镢鐝镢钁军軍钧鈞皲皸俊儁浚濬骏駿咔哢开開锎鐦凯凱剀剴垲塏恺愷铠鎧慨嘅锴鍇忾愾龛龕坎埳侃偘阚闞瞰矙糠粇糠穅闶閌炕匟钪鈧考攷铐銬轲軻疴痾钶鈳颏頦颗顆壳殼咳欬克剋克尅课課骒騍缂緙锞錁肯肎垦墾恳懇坑阬铿鏗抠摳眍瞘叩敂扣釦寇宼库庫绔絝喾嚳裤褲夸誇块塊侩儈郐鄶哙噲狯獪脍膾宽寬髋髖款欵诓誆诳誑邝鄺圹壙纩纊况況旷曠矿礦矿鑛贶貺亏虧岿巋窥窺窥闚匮匱愦憒愧媿溃潰蒉蕢馈餽馈饋篑簣聩聵坤堃昆崐昆崑锟錕鲲鯤捆梱捆綑阃閫困睏扩擴阔濶阔闊腊臘蜡蠟辣辢来來崃崍徕徠涞淶莱萊铼錸赉賚睐睞赖賴赖顂濑瀨癞癩籁籟兰蘭岚嵐拦攔栏欄婪惏阑闌蓝藍谰讕澜瀾褴襤斓斕篮籃镧鑭览覽揽攬缆纜榄欖懒嬾懒懶烂爛滥濫琅瑯锒鋃螂蜋阆閬捞撈劳勞唠嘮崂嶗痨癆铹鐒铑銠涝澇耢耮乐樂鳓鰳缧縲镭鐳诔誄垒壘泪淚类類累纍棱稜厘釐梨棃狸貍离離骊驪犁犂鹂鸝漓灕缡縭蓠蘺璃琍璃瓈鲡鱺篱籬藜蔾礼禮里裡里裏逦邐锂鋰鲤鯉鳢鱧历厤历曆历歷厉厲丽麗励勵呖嚦坜壢沥瀝苈藶枥櫪疠癘隶隷隶隸俪儷栎櫟疬鬁疬癧荔茘轹轢郦酈栗慄砺礪砾礫莅涖莅蒞粝糲蛎蠣跞躒雳靂俩倆奁匲奁奩奁匳奁籢连連帘簾怜憐涟漣莲蓮联聯裢褳廉亷鲢鰱镰鐮敛斂敛歛琏璉脸臉裣襝蔹蘞练練娈孌炼煉炼鍊恋戀殓殮链鏈潋瀲凉涼梁樑粮糧两兩魉魎谅諒辆輛辽遼疗療缭繚镣鐐鹩鷯钌釕猎獵邻鄰邻隣临臨淋痳辚轔磷粦磷燐鳞鱗麟麐凛凜廪廩懔懍檩檁吝恡赁賃蔺藺躏躪灵霛灵靈岭嶺凌淩铃鈴棂櫺棂欞绫綾菱蔆龄齡鲮鯪领領溜霤刘劉浏瀏留畱琉瑠琉璢馏餾骝騮瘤癅镏鎦柳栁柳桺绺綹锍鋶鹨鷚龙龍咙嚨泷瀧茏蘢栊櫳珑瓏胧朧砻礱笼籠聋聾陇隴垄壟垄壠拢攏娄婁偻僂喽嘍蒌蔞楼樓耧耬蝼螻髅髏嵝嶁搂摟篓簍瘘瘺瘘瘻镂鏤噜嚕撸擼卢盧庐廬芦蘆垆壚垆罏泸瀘炉爐炉鑪栌櫨胪臚轳轤鸬鸕舻艫颅顱鲈鱸卤鹵卤滷虏虜掳擄鲁魯橹櫓橹艣橹艪镥鑥陆陸录錄赂賂辂輅渌淥禄祿滤濾戮剹辘轆鹭鷺氇氌驴驢闾閭榈櫚吕呂侣侶稆穭铝鋁屡屢缕縷褛褸虑慮绿綠孪孿峦巒挛攣栾欒鸾鸞脔臠滦灤銮鑾乱亂略畧锊鋝抡掄仑侖仑崙伦倫囵圇沦淪纶綸轮輪论論罗羅罗儸猡玀脶腡萝蘿逻邏椤欏锣鑼箩籮骡騾骡驘镙鏍裸躶裸臝泺濼络絡荦犖骆駱妈媽嬷嬤麻蔴蟆蟇马馬犸獁玛瑪码碼蚂螞杩榪骂罵骂駡唛嘜吗嗎买買荬蕒劢勱迈邁麦麥卖賣脉脈脉衇颟顢蛮蠻馒饅瞒瞞鳗鰻满滿螨蟎谩謾缦縵镘鏝猫貓牦氂牦犛锚錨铆鉚冒冐贸貿帽夘帽戼么麼没沒梅楳梅槑镅鎇鹛鶥霉黴镁鎂门門扪捫钔鍆闷悶焖燜懑懣们們蒙懞蒙濛蒙矇锰錳梦夢弥彌弥瀰祢禰猕獼谜謎芈羋眯瞇觅覓觅覔秘祕幂冪谧謐绵綿绵緜黾黽缅緬腼靦面靣面麪面麵鹋鶓缈緲妙玅庙廟咩哶灭滅蔑衊珉瑉缗緍缗緡闵閔泯冺闽閩悯憫愍湣鳘鰵鸣鳴铭銘谬謬缪繆谟謨馍饃馍饝模糢殁歿蓦驀镆鏌谋謀亩畝钼鉬幕幙拿拏拿挐镎鎿内內纳納钠鈉乃廼乃迺奶嬭难難楠枏楠柟馕饢挠撓铙鐃蛲蟯垴堖恼惱脑腦闹閙闹鬧讷訥馁餒嫩嫰铌鈮霓蜺鲵鯢你妳拟擬昵暱腻膩鲇鮎鲶鯰捻撚辇輦撵攆念唸娘孃酿釀鸟鳥茑蔦袅嫋袅裊袅嬝捏揑陧隉聂聶啮嚙啮齧嗫囁镊鑷镍鎳颞顳蹑躡孽孼宁寧咛嚀拧擰狞獰柠檸聍聹泞濘纽紐钮鈕农農农辳侬儂哝噥浓濃脓膿弄衖驽駑钕釹疟瘧暖煖暖煗傩儺诺諾锘鍩讴謳欧歐殴毆瓯甌鸥鷗呕嘔怄慪沤漚盘槃盘盤蹒蹣庞龐刨鉋刨鑤狍麅炮砲炮礮疱皰胚肧赔賠锫錇佩珮辔轡喷噴鹏鵬碰掽碰踫纰紕铍鈹毗毘罴羆骈駢谝諞骗騗骗騙缥縹飘飃飘飄贫貧嫔嬪频頻颦顰评評凭凴凭憑苹蘋瓶缾鲆鮃钋釙泼潑颇頗钷鉕迫廹仆僕扑撲铺鋪铺舖镤鏷朴樸谱譜镨鐠凄悽凄淒栖棲桤榿戚慼戚鏚齐齊脐臍颀頎骐騏骑騎棋棊棋碁蛴蠐旗旂蕲蘄鳍鰭岂豈启啓启啟绮綺气氣讫訖弃棄荠薺碛磧憩憇千韆扦扡迁遷佥僉钎釺牵牽悭慳铅鉛谦謙愆諐签簽签籤骞騫荨蕁钤鈐钱錢钳鉗乾亁乾乹潜潛浅淺肷膁谴譴缱繾堑塹椠槧呛嗆羌羗戗戧枪槍跄蹌锖錆锵鏘镪鏹强彊强強墙墻墙牆嫱嬙蔷薔樯檣樯艢抢搶羟羥襁繈襁繦炝熗硗墝硗磽跷蹺锹鍫锹鍬缲繰乔喬侨僑荞蕎桥橋谯譙憔癄憔顦鞒鞽诮誚峭陗窍竅翘翹窃竊惬愜箧篋锲鍥亲親钦欽琴琹勤懃锓鋟寝寢吣唚揿搇揿撳氢氫轻輕倾傾鲭鯖苘檾顷頃请請庆慶穷窮茕煢琼瓊丘坵秋秌秋鞦鳅鰌鳅鰍虬虯球毬赇賕巯巰区區曲粬曲麯岖嶇诎詘驱敺驱驅躯軀趋趨鸲鴝癯臒龋齲阒闃觑覰觑覷觑覻权權诠詮辁輇铨銓蜷踡颧顴绻綣劝勸却卻悫愨悫慤确確阕闋阙闕鹊鵲榷搉裙帬裙裠群羣冉冄让讓荛蕘饶饒桡橈扰擾娆嬈绕繞热熱认認纫紉妊姙轫軔韧靭韧韌饪飪绒毧绒絨绒羢荣榮嵘嶸蝾蠑融螎冗宂铷銣颥顬缛縟软軟软輭蕊蕋蕊橤蕊蘂锐銳睿叡闰閏润潤箬篛洒灑飒颯萨薩腮顋鳃鰓赛賽毵毿伞傘伞繖糁糝馓饊颡顙丧喪骚騷缫繅鳋鰠扫掃涩澁涩澀啬嗇铯銫穑穡杀殺纱紗铩鎩鲨鯊筛篩晒曬删刪姗姍钐釤膻羶闪閃陕陝讪訕骟騸缮繕膳饍赡贍鳝鱓鳝鱔伤傷殇殤觞觴垧坰赏賞绱緔烧燒绍紹赊賒蛇虵舍捨厍厙设設慑慴慑懾摄攝滠灄绅紳诜詵审審审讅谂諗婶嬸渖瀋肾腎渗滲升昇升陞声聲胜勝渑澠绳繩圣聖剩賸尸屍师師虱蝨诗詩狮獅湿溼湿濕酾釃鲺鯴时時识識实實蚀蝕埘塒莳蒔鲥鰣驶駛势勢视眎视眡视視试試饰飾是昰柿柹贳貰适適轼軾铈鈰谥諡谥謚释釋寿壽寿夀兽獸绶綬书書纾紓枢樞倏倐倏儵疏疎摅攄输輸赎贖薯藷术術树樹竖竪竖豎庶庻数數漱潄帅帥闩閂双雙谁誰税稅顺順说說说説烁爍铄鑠硕碩丝絲咝噝鸶鷥缌緦蛳螄厮廝锶鍶似佀祀禩饲飼驷駟俟竢松鬆怂慫耸聳讼訟诵誦颂頌搜蒐馊餿飕颼锼鎪擞擻薮藪苏甦苏蘇苏囌稣穌诉訴肃肅谡謖溯泝溯遡酸痠虽雖绥綏随隨岁嵗岁歲谇誶孙孫狲猻荪蓀飧飱损損笋筍挲挱蓑簑缩縮唢嗩琐瑣锁鎖它牠铊鉈塔墖獭獺鳎鰨挞撻闼闥骀駘台臺台颱台檯抬擡鲐鮐态態钛鈦贪貪摊攤滩灘瘫癱坛墰坛壇坛罈坛壜坛罎昙曇谈談锬錟谭譚袒襢钽鉭叹嘆叹歎赕賧汤湯铴鐋镗鏜饧餳糖餹傥儻烫燙趟蹚涛濤绦絛绦縚绦縧掏搯韬韜鼗鞀鼗鞉讨討铽鋱腾騰誊謄藤籐锑銻绨綈啼嗁缇緹鹈鵜题題蹄蹏体躰体體屉屜剃薙剃鬀阗闐条條龆齠鲦鰷眺覜粜糶铫銚贴貼铁鉄铁銕铁鐵厅厛厅廳听聼听聽烃烴铤鋌同衕铜銅统統筒筩恸慟偷偸偷媮头頭秃禿图圖涂凃涂塗钍釷兔兎团團团糰抟摶颓頹颓頽颓穨腿骽蜕蛻饨飩臀臋托託拖拕脱脫驮馱驼駝鸵鴕鼍鼉椭橢拓搨箨籜洼窪娲媧蛙鼃袜襪袜韤腽膃弯彎湾灣纨紈玩翫顽頑挽輓绾綰碗盌碗椀万萬亡亾网網往徃辋輞望朢为為为爲韦韋围圍帏幃沩溈沩潙违違闱闈涠潿维維潍濰伟偉伪偽伪僞纬緯苇葦炜煒玮瑋诿諉韪韙鲔鮪卫衛卫衞谓謂喂餧喂餵猬蝟温溫纹紋闻聞蚊螡蚊蟁阌閿吻脗稳穩问問瓮甕瓮罋挝撾涡渦莴萵窝窩蜗蝸卧臥龌齷乌烏污汙污汚邬鄔呜嗚诬誣钨鎢无無吴吳芜蕪坞塢坞隖妩娬妩嫵庑廡忤啎怃憮鹉鵡务務误誤骛騖雾霧鹜鶩诶誒牺犧晰晳溪谿锡錫嘻譆膝厀习習席蓆袭襲觋覡玺璽铣銑戏戯戏戲系繫系係饩餼细細郄郤阋鬩舄潟虾蝦侠俠峡峽狭狹硖硤辖轄辖鎋吓嚇厦廈仙僊纤縴纤纖籼秈莶薟跹躚锨鍁鲜鮮闲閒闲閑弦絃贤賢咸鹹娴嫺娴嫻衔啣衔銜痫癇鹇鷳鹇鷴鹇鷼显顯险險猃獫蚬蜆藓蘚县縣岘峴苋莧现現线綫线線宪憲馅餡羡羨献獻乡鄉乡鄕芗薌厢廂缃緗骧驤镶鑲详詳享亯响響饷餉飨饗鲞鯗向嚮向曏项項枭梟哓嘵骁驍绡綃萧蕭销銷潇瀟箫簫嚣嚻嚣囂晓曉筱篠效効效傚啸嘯啸歗蝎蠍协協邪衺胁脅胁脇挟挾谐諧携擕携攜撷擷缬纈鞋鞵写寫泄洩泻瀉绁紲绁絏绁緤亵褻谢謝蟹蠏欣訢锌鋅衅釁兴興陉陘幸倖凶兇汹洶胸胷修脩鸺鵂馐饈绣綉绣繡锈銹锈鏽须須须鬚顼頊虚虛嘘噓许許诩詡叙敍叙敘恤卹恤賉勖勗绪緒续續婿壻溆漵轩軒谖諼喧諠萱萲萱蕿萱藼萱蘐悬懸旋鏇璇璿选選癣癬绚絢铉鉉楦楥靴鞾学學泶澩鳕鱈谑謔勋勛勋勳埙塤埙壎熏燻寻尋巡廵驯馴询詢浔潯鲟鱘训訓讯訊徇狥逊遜丫枒压壓鸦鴉鸦鵶桠椏鸭鴨哑啞痖瘂亚亞讶訝垭埡娅婭氩氬咽嚥恹懨恹懕烟煙胭臙阉閹腌醃讠訁闫閆严嚴岩喦岩巖岩巗盐鹽阎閻颜顏颜顔檐簷兖兗俨儼厣厴演縯魇魘鼹鼴厌厭彦彥砚硯艳艷艳豔验騐验驗谚諺焰燄雁鴈滟灧滟灩酽釅谳讞餍饜燕讌燕醼燕鷰赝贋赝贗鸯鴦扬揚扬敭扬颺阳陽杨楊炀煬疡瘍养養痒癢样樣夭殀尧堯肴餚轺軺窑窯窑窰谣謠摇搖遥遙瑶瑤鳐鰩药葯药藥鹞鷂耀燿爷爺铘鋣野埜野壄业業叶葉页頁邺鄴夜亱晔曄烨燁烨爗谒謁靥靨医毉医醫咿吚铱銥仪儀诒詒迤迆饴飴贻貽移迻遗遺颐頤彝彜彝彞钇釔舣艤蚁螘蚁蟻义義亿億忆憶艺藝议議异異呓囈呓讛译譯峄嶧怿懌绎繹诣詣驿驛轶軼谊誼缢縊瘗瘞镒鎰翳瞖镱鐿因囙阴陰阴隂荫蔭荫廕殷慇铟銦喑瘖堙陻吟唫淫婬淫滛银銀龈齦饮飲隐隱瘾癮应應莺鶯莺鸎婴嬰嘤嚶撄攖缨纓罂甖罂罌樱櫻璎瓔鹦鸚鹰鷹茔塋荥滎荧熒莹瑩萤螢营營萦縈滢瀅蓥鎣潆瀠蝇蠅赢贏颍潁颖穎瘿癭映暎哟喲佣傭拥擁痈癰雍雝墉鄘镛鏞鳙鱅咏詠涌湧恿惥恿慂踊踴优優忧憂犹猶邮郵莜蓧莸蕕铀鈾游遊鱿魷铕銪佑祐诱誘纡紆余餘欤歟鱼魚娱娛谀諛渔漁嵛崳逾踰觎覦舆輿与與伛傴屿嶼俣俁语語龉齬驭馭吁訏吁籲妪嫗饫飫郁鬱狱獄钰鈺预預欲慾谕諭阈閾御禦鹆鵒愈瘉愈癒蓣蕷誉譽鹬鷸鸢鳶鸳鴛渊淵员員园園圆圓缘緣鼋黿猿猨猿蝯辕轅橼櫞远遠愿願约約岳嶽钥鑰钥鈅钥籥悦悅钺鉞阅閱阅閲跃躍粤粵云雲匀勻纭紜芸蕓郧鄖氲氳陨隕殒殞运運郓鄆恽惲晕暈酝醖酝醞愠慍韫韞韵韻蕴蘊匝帀杂襍杂雜灾災灾烖灾菑载載簪簮咱偺咱喒攒欑攒儹攒攢趱趲暂暫赞賛赞贊赞讚錾鏨瓒瓚赃賍赃贓赃贜驵駔脏髒脏臟葬塟糟蹧凿鑿枣棗灶竈皂皁唣唕噪譟则則择擇泽澤责責啧嘖帻幘箦簀赜賾贼賊谮譖缯繒锃鋥赠贈揸摣齄齇扎紥扎紮札剳札劄轧軋闸牐闸閘铡鍘诈詐栅柵榨搾斋齋债債沾霑毡氈毡氊谵譫斩斬盏盞崭嶄辗輾占佔战戰栈棧绽綻骣驏张張獐麞涨漲帐帳胀脹账賬钊釗诏詔赵趙棹櫂照炤哲喆辄輒蛰蟄谪謫谪讁辙轍锗鍺这這浙淛鹧鷓贞貞针針针鍼侦偵浈湞珍珎桢楨砧碪祯禎诊診轸軫缜縝阵陣鸩鴆赈賑镇鎮争爭征徴峥崢挣掙狰猙钲鉦睁睜铮錚筝箏证証证證诤諍郑鄭帧幀症癥卮巵织織栀梔执執侄妷侄姪职職絷縶跖蹠踯躑只衹只隻址阯纸紙轵軹志誌制製帙祑帙袠帜幟质質栉櫛挚摯致緻贽贄轾輊掷擲鸷鷙滞滯骘騭稚稺稚穉置寘觯觶踬躓终終钟鈡钟鍾钟鐘肿腫种種冢塚众眾众衆诌謅周週轴軸帚箒纣紂咒呪绉縐昼晝荮葤皱皺骤驟朱硃诛誅诸諸猪豬铢銖槠櫧潴瀦橥櫫烛燭属屬煮煑嘱囑瞩矚伫佇伫竚苎苧注註贮貯驻駐筑築铸鑄箸筯专專砖塼砖甎砖磚颛顓转轉啭囀赚賺撰譔馔饌妆妝妆粧庄莊桩樁装裝壮壯状狀骓騅锥錐坠墜缀綴缒縋赘贅谆諄准準桌槕斫斮斫斲斫斵浊濁诼諑镯鋜镯鐲兹茲兹玆赀貲资資缁緇谘諮辎輜锱錙龇齜鲻鯔姊姉渍漬眦眥综綜棕椶踪蹤鬃騣鬃鬉总縂总總偬傯纵縱粽糉邹鄒驺騶诹諏鲰鯫镞鏃诅詛组組躜躦缵纘纂篹钻鉆钻鑽罪辠樽罇鳟鱒";
    /**
     * 用于存储0-9大写的哈希表
     */
    private static final Map<Character, String> zerotoNineHt;

    /**
     * 用于存储拾佰仟大写的哈希表
     */
    private static final Map<Integer, String> thHuTenHt;

    /**
     * 静态构造函数，用于初始化zerotoNineHt和thHuTenHt
     */
    static {
        // 初始化zerotoNineHt
        zerotoNineHt = new Hashtable<Character, String>();
        zerotoNineHt.put('0', "零");
        zerotoNineHt.put('1', "一");
        zerotoNineHt.put('2', "二");
        zerotoNineHt.put('3', "三");
        zerotoNineHt.put('4', "四");
        zerotoNineHt.put('5', "五");
        zerotoNineHt.put('6', "六");
        zerotoNineHt.put('7', "七");
        zerotoNineHt.put('8', "八");
        zerotoNineHt.put('9', "九");

        // 初始化thHuTenHt
        thHuTenHt = new Hashtable<Integer, String>();
        thHuTenHt.put(0, "");
        thHuTenHt.put(1, "十");
        thHuTenHt.put(2, "百");
        thHuTenHt.put(3, "千");
        thHuTenHt.put(4, "万");
        thHuTenHt.put(5, "十");
        thHuTenHt.put(6, "百");
        thHuTenHt.put(7, "千");
        thHuTenHt.put(8, "亿");
    }

    /**
     * 数字转换成中文，支持到亿
     *
     * @param digital
     * @return
     */
    public static String digitalToStr(String digital) {
        StringBuffer desc = new StringBuffer();
        String digDesc = String.valueOf(digital);
        //boolean lastIsZero = false;
        for (int i = 0; i < digDesc.length(); i++) {
            String zeroToNine = zerotoNineHt.get(digDesc.charAt(i));
            /*if(zeroToNine.equals("零")){
                lastIsZero = true;
    			continue;
    		}
    		if(lastIsZero&&!zeroToNine.equals("零")){
    			desc.append("零");
    			lastIsZero = false;
    		}*/
            desc.append(zeroToNine);
            //desc.append(thHuTenHt.get(digDesc.length()-i-1));
        }
        return desc.toString();
    }

    /**
     * 全角半角转换
     * 大小写转换
     * 繁体转简体
     *
     * @param str
     * @return
     */
    public static String convert(String str) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (isSpecialCharater(ch)){
            	sb.append(" ");
            	continue;
            }                
            ch = largeToSmall(ch);
            ch = fullToHalf(ch);
            ch = traditionToSimple(ch);
            sb.append(ch);
        }
        return sb.toString();
    }

    /**
     * 数字转汉字
     *
     * @param str
     * @return
     */
    public static String convertDigit(String str) {
        StringBuffer sb = new StringBuffer();
        StringBuffer changeNum = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (isSpecialCharater(ch))
                continue;
            if (Character.isDigit(ch)) {
                changeNum.append(ch);
                if (str.length() - 1 == i) {
                    sb.append(digitalToStr(changeNum.toString()));
                }
                continue;
            }
            if (changeNum.length() > 0) {
                sb.append(digitalToStr(changeNum.toString()));
                changeNum = new StringBuffer();
            }
            ch = largeToSmall(ch);
            ch = fullToHalf(ch);
            ch = traditionToSimple(ch);
            sb.append(ch);
        }
        return sb.toString();
    }

    /**
     * 繁转简
     *
     * @param input
     * @return
     */
    public static char traditionToSimple(char input) {
        int index = simpleAndTiadition.indexOf(input);
        if (index % 2 == 1) {
            input = simpleAndTiadition.charAt(index - 1);
        }
        return input;
    }


    /**
     * 转换为小写
     *
     * @param input
     * @return
     */
    public static char largeToSmall(char input) {
        if (input >= 'A' && input <= 'Z') {
            input += 32;
        }
        return input;
    }

    /**
     * 全角转半角
     *
     * @param input
     * @return
     */
    public static char fullToHalf(char input) {
        if (input > 65280 && input < 65375) {
            input = (char) (input - 65248);
        }
        return input;
    }

    public static boolean isSpecialCharater(char inputChar) {
        return !(isCJKCharacter(inputChar) || isArabicNumber(inputChar) || isEnglishLetter(inputChar));
    }

    public static boolean isEnglishLetter(char input) {
        return (input >= 'a' && input <= 'z')
                || (input >= 'A' && input <= 'Z');
    }

    public static boolean isArabicNumber(char input) {
        return input >= '0' && input <= '9';
    }

    public static boolean isCJKCharacter(char input) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(input);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                //全角数字字符和日韩字符
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                //韩文字符集
                || ub == Character.UnicodeBlock.HANGUL_SYLLABLES
                || ub == Character.UnicodeBlock.HANGUL_JAMO
                || ub == Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO
                //日文字符集
                || ub == Character.UnicodeBlock.HIRAGANA //平假名
                || ub == Character.UnicodeBlock.KATAKANA //片假名
                || ub == Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS
                ) {
            return true;
        } else {
            return false;
        }
        //其他的CJK标点符号，可以不做处理
        //|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
        //|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
    }

    public static void main(String[] args) {
        System.out.println(convertDigit("暴走漫画—暴走大事件二"));
        String regex_shuzi = "第?[一二三四五六七八九十]+(集|季|部|版)";
        String regex_shuzi_e = "[一二三四五六七八九十百千零万]+$";
        System.out.println("天天一向上二千零一十三".replaceAll(regex_shuzi, "").replaceAll(regex_shuzi_e, ""));
        System.out.println("天天向上二千零一".matches(".*[一二三四五六七八九十]{2,}$"));
        System.out.println("天天向上二千零一十三集".matches(".*第?(\\d+|[一二三四五六七八九十百千零]+)?(集|部|季|版)?$"));

        String theWord = "威尔史密斯	2";
        int indexTab = theWord.indexOf("\t");
        if (indexTab > 0) {
            theWord = RegularizeUtil.convertDigit(theWord.substring(0, indexTab)) + theWord.substring(indexTab);
        }
        System.out.println(theWord);
    }
}
