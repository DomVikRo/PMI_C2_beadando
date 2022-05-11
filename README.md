# PMI_C2_beadando
#általános tudni valók
A program 4 fájlt tartalmaz.\
Ezek:
* main-Ez tartalmazz a fő menüt melyben képes a felhasználó választani milyen műveletet szeretne véget tartani\
* com-Ez tartalmazza az összes elérhető parancsot és lépéseket egyes feladatok elkészítéséhez.\
* diakok-3 adatot tárol a diák nevét annak jegyeit és hogy a jegyek alapján átment-e\
* dk.xml-innen olvassa be a program be az adatokat annak elindításakor és menti annak a leállításakor
##com:
* ad-elem hozzáadásáért felel\
* kiír-az összes listában lévő elem kiírásáért felel\
* update-Ez felel a kívánt adatok bővítéséért illetve azok teljesen megváltoztatásáért\
* atlag-ez egy átlag számító mely a folyamat végén elmenti hogy a jegyek alapján átment-e az adott diák\
* delete-diák törlése\
* save-a program leállításakor ez menti el a módósításokat\
* beolvas-ez olvassavassa be a mentett adatokat\
* hozzaad-ez az új diák hozzáadásához szükséges adatokat kéri be\
* inputname és inputjegy mind 2 a nevükben is szereplő adatot kérik be a felhasználótól
##dk.xml-a diákok rövidítése
itt vannak eltárolva az adatok melyek:\
* A tanuló neve (name,Stringben tárolt)
* A tanuló jegyei(jegyek,Stringben tárolt amit a kívánt szakaszokban felbont a program kisebb részekre és int-re cserél)
* A tanuló átment vagy sem(atment,int-ben tárol ami a kiírás során String lesz a felhasználó átláthatóságának megkönítésének érdekében)
##main
itt van a főmenű és itt olvass be a program a mentett adatokat
