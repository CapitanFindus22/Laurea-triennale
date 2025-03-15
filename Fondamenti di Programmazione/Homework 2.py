def ex1(poem_filename):
    
    with open(poem_filename, 'r', encoding='utf-8') as testo:
        
        righe = togli_char_speciali(list(testo.readlines()))
        
        return trova_prosodia(righe)
        
        
def togli_char_speciali(righe):
        
        diz1 = {'à':'a','á':'a','â':'a','ã':'a','ä':'a','å':'a','è':'e','é':'e','ê':'e','ë':'e','ì':'i','í':'i','î':'i','ï':'i','ò':'o','ó':'o','ô':'o','õ':'o','ö':'o','ø':'o','ù':'u','ú':'u','û':'u','ü':'u','ý':'y','ÿ':'y'}
        
        diz2 = {' ':''}
        
        lettere = 'abcdefghijklmnopqrstuvwxyz'
        
        lettere2 = 'àáâãäåèéêëìíîïòóôõöøùúûüýÿ'
        
        for i in range(33,97):
            
            diz2[chr(i)] = ''
            
        for i in range(123,256):
            
            diz2[chr(i)] = ''     
            
        i = 0
        
        for riga in righe:
            
            riga = riga.lower()
            
            #Rimuovo il \n alla fine della riga se presente
            
            if riga.endswith('\n'):
                
                riga = riga.replace('\n','')
                
            temp = ''
                
            for char in riga:
                
                if char in lettere:
                    
                    temp += char
                    
                elif char in lettere2:
                    
                    temp += diz1[char]
                    
                else:
                    
                    temp += diz2[char]
                
            righe[i] = temp
                
            i += 1
                
        return righe 

def trova_prosodia(righe):
    
    vocali = 'aeiouyj'
    
    ES = []
    
    finali = []
    
    rima = {}
    
    prosodia = []
    
    modulo = set()
    
    stato = 0
    
    j = 0
    
    #Calcolo il finale della riga e la lunghezza della stessa
    
    for riga in righe:
        
        i = 0
        
        el_sonori = []
        
        el_sonori.append('')
        
        #Divido la parola fino a quando non passo da una vocale ad una consonante (stato 1 --> 0),
        #il primo if serve per le righe che iniziano con una vocale
            
        stato = 0
            
        for char in riga:
            
            if char in vocali:
                
                el_sonori[i] += char
                
                stato = 1
                
            elif stato == 0:
                
                el_sonori[i] += char
                
            else:
                
                stato = 0
                
                el_sonori.append(char)
                
                i += 1
 
        #Aggiungo: finale, ES in base ai valori calcolati   
         
        finali.append(el_sonori[i])
        
        ES.append(len(el_sonori))
        
        j += 1
   
    #Calcolo la prosodia     
   
    i = 0 
    
    j = 0
        
    for fin in finali:
        
        if rima.get(fin+str(ES[j]),-1) == -1:
            
            rima[fin+str(ES[j])] = i
            
            prosodia.append(rima[fin+str(ES[j])])
            
            i += 1
            
            j += 1
            
        else:
            
            prosodia.append(rima[fin+str(ES[j])])
            
            j += 1
       
    #Calcolo le sequenze da controllare per trovare il modulo, le aggiungo
    #solo se la lunghezza della sequenza non lascia valori fuori
       
    a = [prosodia[0:x] for x in range(3,1 + len(prosodia)//2) if len(prosodia)%x == 0]
                
    b = [prosodia[x:x+x] for x in range(4, len(prosodia)) if len(prosodia)%x == 0]
     
    j = 0
               
    #Converto le sequenze di b come già fatto per la prosodia
        
    for el in b:

        yeet = {}
                
        i = 0
        
        temp = []
                
        for val in el:
                
            if yeet.get(val,-1) == -1:
                    
                yeet[val] = i
                    
                i += 1
            
            temp.append(yeet[val])
            
        b[j] = temp
        
        j += 1         
        
    #Confronto le sequenze in a e b e nel caso siano uguali aggiungo la lunghezza 
    #di el al modulo
    
    for el in a:
            
        ind = len(el)
        
        for el1 in b:
                
            if el == el1 and ES[0:ind-1] == ES[ind:(ind*2)-1]:
                
                modulo.add(len(el)) 
    
    return prosodia,min(modulo),ES,finali
