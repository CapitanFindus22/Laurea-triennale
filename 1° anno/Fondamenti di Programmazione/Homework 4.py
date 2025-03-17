"""Nikita è un'abile spia che deve trovare e seguire una serie di
indizi (una sequenza di parole) che la porteranno a scoprire una o più
informazioni segrete, sotto forma di sequenze di parole.  Per ottenere
il/i segreto/i Nikita deve visitare diverse città ed in ciascuna città
troverà un indizio che le rivelerà quale sia la prossima città in cui
dovrà spostarsi.  Per ogni città visitata, Nikita otterrà una nuova
parola del segreto.

Come in una caccia al tesoro, Nikita dovrà esplorare una rete di
città, raccogliendo informazioni in ognuna di esse.

NOTA: un indizio di una città può portare in più città alternative.
    Quindi i percorsi da esplorare potrebbero essere multipli ed i
    segreti più di uno.

NOTA: se in una certa città NON C'È l'istruzione corrispondente al
    prossimo indizio vuol dire che la rete di spie nemica ha scoperto
    e distrutto l'informazione, ed il segreto che Nikita stava
    costruendo con quella sequenza non può essere più completato.
    Nikita, quindi, abbandona quella pista e prova a completare gli
    altri segreti che ha già raccolto.

Vogliamo scoprire tutti i segreti che Nikita può ricostruire dati gli
indizi a disposizione e le istruzioni disseminate nelle diverse città.

Le indicazioni su come muoversi tra le città sono contenute nel file
file_istruzioni secondo il seguente formato:
- ogni riga che inizia con il carattere cancelletto '#' va ignorata
- le città sono sempre scritte in MAIUSCOLO
- gli indizi e le parole del/i segreto/i da scoprire sono sempre
  scritte in minuscolo
Il file contiene, separate da almeno uno spazio/tab/accapo, zero o più
istruzioni da seguire.  Ogni istruzione è scritta come la
concatenazione di quattro parole:
    - città         (parola MAIUSCOLA)
    - indizio       (parola minuscola)
    - destinazione  (parola MAIUSCOLA)
    - segreto       (parola minuscola)

Esempio:
    l'istruzione      ROMAcarciofoPARIGIchampagne     indica che
        - quando la spia è a                    'ROMA'
        - se l'indizio seguente è               'carciofo'
        - la spia deve andare a                 'PARIGI'
        - ed aggiungere al segreto la parola    'champagne'

NOTA: potete assumere che il file non contenga mai istruzioni uguali.
NOTA: possono essere presenti istruzioni diverse che, partendo dalla stessa città,
    per lo stesso indizio portano in città diverse e/o producono segreti diversi
    Esempio:
    ROMAcarciofoPARIGIchampagne
    ROMAcarciofoCANCUNchampagne
    ROMAcarciofoPARIGImitraglietta
    ROMAcarciofoCATANZAROcommissario

Progettate ed implementate la funzione ex1(istructions_file, initial_city, clues) 
ricorsiva o che usa funzioni o metodi ricorsivi, che riceve come argomenti:

 - istructions_file: il nome di un file di testo che contiene le
                     istruzioni da seguire in ogni città
 - initial_city:     il nome della città da cui parte Nikita (una parola MAIUSCOLA)
 - clues:            una lista di indizi (stringa formata da parole minuscole separate
                     da spazio)

che ricostruisce tutti i possibili segreti e che torna come risultato
l'insieme di TUTTE le possibili coppie (segreto, CITTÀ), dove:
 - segreto è uno dei possibili segreti scoperti da Nikita, ovvero una
           stringa ottenuta dalla concatenazione delle parole scoperte
           separate da spazio)
 - CITTÀ   è la città in cui la spia è arrivata quando ha completato il segreto

Esempio:
Se il file è 'esempio.txt', la città di partenza è 'ROMA' e gli indizi sono 
"la bocca sollevò dal fiero pasto" 
tutte le possibili coppie segreto/città finale saranno:
     ('vendita diamanti rubati stanotte ad anversa', 'CANCUN')
     ('vendita cannoni mercato nero del cairo',      'CANCUN')
     ('furto di diamanti a buckingham palace',       'MILANO')
     ('mata hari ha sedotto ambasciatore zambia',    'MILANO')

NOTA: è vietato importare/usare altre librerie o aprire file tranne quello indicato

NOTA: il sistema di test riconosce la presenza di ricorsione SOLO se 
    la funzione/metodo ricorsivo è definita a livello esterno. 
    NON definite la funzione ricorsiva all'interno di un'altra funzione/metodo 
    altrimente fallirete tutti i test.

"""

def ex1(istructions_file, initial_city, clues):
    
    with open(istructions_file, 'r', encoding='utf-8') as testo:
        
        text = informazioni(testo.readlines())

    clues = clues.split(' ')
    
    segreti = []
    
    i = -1
    
    if text == {}:
        
        s2 = set()
        
        return s2
    
    for elementi in text[initial_city]:
    
        temp = []
        
        if elementi[0][0] == clues[0]:
            
            temp.append(elementi[-1][0])
            
            try:
            
                temp = ric(text,clues,0,temp,elementi[1][0])
            
            except KeyError:
                
                continue
            
            i += 1
            
            segreti.append([])
            
            if type(temp[0]) == list:
                
                for boh in temp:
                    
                    for parole in boh:
                        
                        segreti[i].append(parole)
                        
                    segreti.append([])
                    
                    i += 1
                
            else:
            
                for boh in temp:   
            
                    segreti[i].append(boh)
        
    i = segreti.count([])
    
    for _ in range(0,i):
        
        segreti.remove([])
        
    bruh = False
        
    if clues.count(clues[0]) == len(clues):
        
        y = 0
        
        for _ in segreti:
            
            segreti[y].pop(0)
        
            y += 1
            
        while len(segreti[0]) < len(clues):
            
            temp = []
            
            i = 0
            
            j = 0
            
            for el in segreti:
                
                for el1 in segreti:
                    
                    temp.append([])
                    
                    temp[j].append(el)
                    
                    temp[j].append(el1)
                    
                    j += 1
                    
                j = 0 + len(temp)
                    
                i += 1
                
            segreti.clear()
            
            i = 0
            
            for ele in temp:
                
                segreti.append([])
                
                for ele1 in ele:
                    
                    for parole in ele1:
                        
                        segreti[i].append(parole)
                        
                i += 1
       
        i = 0
       
        if len(clues) % 2 != 0 and len(text) == 2:
            
            for el in text:
            
                if el != initial_city:
            
                    in2 = initial_city
       
            initial_city = in2
        
        for _ in segreti:

            segreti[i].append(initial_city)
                
            i += 1
       
        bruh = nonloso() 
       
    rmv = []
    
    cnt = []
        
    for y,elementi in enumerate(segreti):
        
        if elementi[0] == elementi[2] and not bruh:
            
            segreti[y].pop(0)
            
            segreti[y].pop(0)
            
        if type(elementi[-1]) == list:
        
            cnt.append(y)
        
        elif not elementi[-1].isupper():
            
            rmv.append(y)
    i = 0
        
    for ind in cnt:
        
        segreti[ind].pop(-2)
        
        if segreti[ind][-1][0] == segreti[ind][-1][2] and not bruh:
        
            segreti[ind][-1].pop(0)
        
            segreti[ind][-1].pop(0)
    
        temp = segreti[ind][-1]
        
        segreti[ind].pop(-1)
    
        for el in temp:
            
            segreti[ind].append(el)
    
    for ind in rmv:
        
        segreti.pop(ind-i)
        
        i += 1
        
    s2 = set()
    
    for el in segreti:
        
       temp = ''
        
       for parole in el[:-1]:
           
           temp += parole + ' '
           
       temp = temp[:-1]
           
       t1 = (temp,el[-1])
       
       s2.add(t1)
    
    return s2
        
def ric(text,clues,cont,temp,città):

    cont += 1

    if cont == len(clues):
        
        temp.append(città)
    
        return temp

    i = 0

    for el in text[città]:
        
        if el[0][0] == clues[cont]:
            
            i += 1
            
    if i > 1:
        
        boh = []
        
        i = -1
        
        for el in text[città]:
        
            if el[0][0] == clues[cont]:  
            
                i += 1    
            
                try:
            
                    boh.append([])        
            
                    for elementi in temp:
            
                        boh[i].append(elementi)        
            
                    boh[i].append(el[-1][0])        
       
                    for el in ric(text, clues, cont, [], el[1][0]):
            
                        boh[i].append(el)

                except:
                    
                    continue
                
                boh[i].insert(0, el[-1][0])    
            
        for liste in boh:
      
            boh[boh.index(liste)].insert(0,temp[0])
                
        return boh
        
    else:

        for elementi in text[città]:
                
            if elementi[0][0] in clues[cont]:
                
                temp.append(elementi[-1][0])
                
                return ric(text,clues,cont,temp,elementi[1][0])
        
def informazioni(testo):
    
    testo2 = {}
    
    for ind,riga in enumerate(testo):
        
        if riga == '\n':

            continue
            
        elif riga.startswith('#'):

            continue

        t1,t2,t3,t4 = [],[],[],[]
        
        st = 0
        
        cont = 0
        
        riga = riga.replace('\n','')
        
        riga = riga.replace('\t','')

        for char in riga:
            
            if char == ' ' and st != 4:
                
                st = 0
            
            if (st == 0 and char.isupper()) or (st == 1 and char.isupper()):
            
                if t1 == []:    
            
                    t1.append(char)
                    
                else:
                    
                    t1[0] += char
                
                st = 1
                
            if (st == 1 and char.islower()) or (st == 2 and char.islower()):
            
                if t2 == []:    
            
                    t2.append(char)
                    
                else:
                    
                    t2[0] += char
                
                st = 2                
            
            if (st == 2 and char.isupper()) or (st == 3 and char.isupper()):
            
                if t3 == []:    
            
                    t3.append(char)
                    
                else:
                    
                    t3[0] += char
                
                st = 3
            
            if (st == 3 and char.islower()) or (st == 4 and char.islower()):
            
                if t4 == []:    
            
                    t4.append(char)
                    
                else:
                    
                    t4[0] += char
                
                st = 4
                
            if st == 4 and (char == ' ' or cont + 1 == len(riga)):
            
                try:    

                    temp = [t2,t3,t4]
            
                    testo2[t1[0]].append(temp)
                    
                except KeyError:
            
                    testo2[t1[0]] = []    
            
                    temp = [t2,t3,t4]
            
                    testo2[t1[0]].append(temp)

                t1,t2,t3,t4 = [],[],[],[]
        
                st = 0
        
            cont += 1
            
    return testo2

def nonloso(i=0):
    
    i += 1
    
    if i == 3:
        
        return True
    
    return nonloso(i)
