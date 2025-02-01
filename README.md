Ako iko ovo zaista gleda ne zamerite sto je pola na srpskom pola na engleskom  
  
Ovaj projekat sadrži tri modula:  
1) Klijent - Aplikacija za krajnjeg korisnika.  
2) Server - Glavni server koji obrađuje zahteve klijenata.  
3) Zajednički - Modul sa zajedničkim klasama i funkcionalnostima.  
  
Za otvaranje projekta u NetBeansu ostavljeni su nbproject folderi.  
U slucaju da to ne radi potrebno za svaki od modula kreirati novi projekat Java with **Ant**.  
  
GENERISANJE I DODAVANJE ZAJEDNIČKOG JAR FAJLA  
  Otvoriti Zajednički modul u NetBeans-u.  
  Iz menija izabrati Run → Clean and Build Project.  
  Nakon izgradnje, pronaći generisani JAR fajl u folderu: Zajednicki/dist/Zajednicki.jar  
  Kopirati Zajednicki.jar u folder lib unutar Klijent i Server modula. ili  
  U NetBeans-u dodati Zajednicki.jar kao biblioteku:  
    Desni klik na projekat (Klijent ili Server) → Properties.  
    U sekciji Libraries, kliknuti na Add JAR/Folder....  
    Izabrati Zajednicki.jar i potvrditi sa OK.  
  
KONFIGURACIJA MYSQL BAZE PODATAKA  
  Preuzeti MySQL konektor sa zvanične stranice:  
    https://dev.mysql.com/downloads/connector/j/  
Kopirati preuzeti .jar fajl u folder lib unutar Server modula.  
Dodati MySQL konektor u NetBeans-u:  
Desni klik na Server projekat → Properties.  
U sekciji Libraries, kliknuti na Add JAR/Folder....  
Izabrati MySQL konektor .jar fajl i potvrditi sa OK.
Proveriti da su tačno podešeni podaci za konekciju u kodu (url, username, password).

U okviru projekta se nalazi SQL fajl (medicinskosredstvo.sql) koji sadrži postojeću simulaciju baze podataka.
Može se iskoristiti za brzo kreiranje baze podataka u MySQL serveru.
Potrebno je pokrenuti SQL fajl u SQLyog-u ili nekom drugom alatu za rad sa bazom.
