## 1. Definirea sistemului
Sistemul gestioneaza o platforma de educatie online. Aplicatia permite utilizatorilor sa isi creeze conturi cu roluri specifice (Cursant, Instructor), sa gestioneze portofele virtuale pentru achizitia cursurilor, sa interactioneze cu materiale de curs (lectii, quiz-uri) si sa obtina recunoastere prin certificate imutabile.

## 1.1 Lista actiuni / interogari
1. **Inregistrare Cursant** - Crearea unui cont de client cu portofel virtual integrat.
2. **Inregistrare Instructor** - Crearea unui cont pentru un creator de continut educational.
3. **Autentificare (Login)** - Verificarea credentialelor (email si parola) pentru accesul in sistem.
4. **Alimentare Portofel** - Adaugarea de fonduri in portofelul virtual al unui Cursant.
5. **Adaugare Curs Nou** - Definirea unui curs cu titlu, pret si categorie (actiune Instructor).
6. **Inrolare la Curs** - Procesul de achizitie a unui curs (verifica fondurile si creeaza legatura).
7. **Adaugare Lectie** - Incarcarea continutului de studiu intr-un curs existent.
8. **Creare Quiz** - Adaugarea unui test de evaluare cu prag de trecere pentru un curs.
9. **Sustinere Quiz** - Validarea cunostintelor de catre un cursant si salvarea scorului.
10. **Generare Certificat** - Emiterea unui document imutabil la finalizarea cursului (progres 100%).
11. **Cautare Curs** - Filtrarea catalogului de cursuri dupa titlu sau categorie.
12. **Adaugare Recenzie** - Oferirea de feedback (rating si comentariu) pentru un curs finalizat.
13. **Afisare Clasament** - Sortarea cursantilor dupa scorurile obtinute la un anumit Quiz.
14. **Modificare Pret** - Actualizarea costului unui curs de catre instructorul titular.
15. **Stergere Curs** - Eliminarea definitiva a unui curs din platforma (actiune Admin).

## 1.2 Lista tipuri de obiecte din domeniu
1. **Utilizator** - Clasa abstracta de baza pentru toti membrii platformei.
2. **Staff** - Clasa intermediara pentru ierarhia administrativa.
3. **Admin** - Utilizator cu drepturi depline de gestionare a sistemului.
4. **Instructor** - Utilizatorul care creeaza si detine cursurile.
5. **Cursant** - Utilizatorul care achizitioneaza si parcurge cursurile.
6. **Curs** - Entitatea principala care grupeaza lectiile si quiz-urile.
7. **Lectie** - Unitatea elementara de continut dintr-un curs.
8. **Quiz** - Instrumentul de evaluare a cunostintelor cursantului.
9. **Inrolare** - Obiectul care face legatura intre un Cursant si un Curs.
10. **ScorQuiz** - Inregistrarea rezultatului obtinut la o sustinere de test.
11. **Certificat** - Clasa imutabila ce reprezinta dovada absolvirii.
12. **Recenzie** - Feedback-ul oferit de utilizatori cursurilor parcurse.