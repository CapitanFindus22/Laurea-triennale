import images

def ex1(image_filename, encoded_filename):
    
    Imm = images.load(image_filename)
    
    In, Fin = Rett(Imm)
  
    w, h = Dim(In,Fin)
    
    Colori = list(In.keys())
    
    Ord = Ordine(Imm,Colori,In,Fin,w,h)
    
    Nuova_imm(Ord,In,Fin,encoded_filename)
    
    x1, y1, x2, y2 = -1, -1, -1, -1
    
    for val in [*In.values()]:

        if x1 == -1:
                
            x1 = val[0]
                
        if val[0] < x1:
                
            x1 = val[0]
                
        if y1 == -1:
                
            y1 = val[1]
                
        if val[1] < y1:
                
            y1 = val[1]      
                
    for val in [*Fin.values()]:

        if x2 == -1:
                
            x2 = val[0]
                
        if val[0] > x2:
                
            x2 = val[0]
                
        if y2 == -1:
                
            y2 = val[1]
                
        if val[1] > y2:
                
            y2 = val[1]         
    
    return x1,y1,x2,y2
    
def Nuova_imm(Ordine,Inizio,Fine,file):
    
    Imm = []
    
    for colori in Ordine:
        
        temp = []
    
        v1, v2, v3, v4 = 0,0,0,0
    
        v1 = Conv(Inizio[colori][0])
    
        v2 = Conv(Inizio[colori][1])
        
        v3 = Conv((Fine[colori][0]-Inizio[colori][0])+1)
        
        v4 = Conv((Fine[colori][1]-Inizio[colori][1])+1)
        
        temp.append(v1)
        
        temp.append(v2)
        
        temp.append(v3)
        
        temp.append(v4)
        
        temp.append(colori)
        
        Imm.append(temp)
        
    images.save(Imm,file)
        
def Conv(Valore):
    
    x1,x2,x3 = 0,0,0
        
    x3 = Valore%256
        
    Valore = Valore//256
        
    x2 = Valore%256
        
    Valore = Valore//256

    x1 = Valore%256       
    
    return x1,x2,x3
  
def Rett(Imm): 
  
    Inizio = {}
    
    Fine = {}
    
    y = 0
    
    for riga in Imm:
        
        x = 0
        
        for pixel in riga:
            
            if pixel != (0,0,0):
                
                if pixel not in Inizio:
                
                    Inizio[pixel] = [x,y]
                
                else:
                    
                    Fine[pixel] = [x,y]
                    
            x += 1
                
        y += 1
                   
    return [Inizio,Fine]

def Dim(In,Fin):
    
    w = []
    
    h = []
    
    i = 0
    
    val2 = [*Fin.values()]
    
    for x in [*In.values()]:
        
        w.append(val2[i][0] - x[0])
        
        h.append(val2[i][1] - x[1])
        
        i += 1
        
    return w, h
    
def Ordine(Immagine,Colori,Inizio,Fine,wid,h):
    
    Ordine = {}
  
    for boh in range(2):
  
        x = 0
        
        y = 0
        
        z = 0
    
        temp = 0    
    
        # Inizio --> Fine
    
        for Punti in list(Inizio.values()):
            
            if boh%2 == 1:

                temp = h[x]
                
                p1 = Immagine[Punti[1] + temp + 1][Punti[0]:Punti[0] + wid[z]]
                p2 = Immagine[Punti[1] + temp - 1][Punti[0]:Punti[0] + wid[z]]
                
            else:
            
                p1 = Immagine[Punti[1] - 1][Punti[0]:Punti[0] + wid[z]]
                p2 = Immagine[Punti[1] + 1][Punti[0]:Punti[0] + wid[z]]
            
            z += 1
            
            w = 0
            
            for pixel in Immagine[Punti[1] + temp][Punti[0]:Punti[0] + wid[x]]:
                
                if pixel != Colori[y] and Colori[y] not in Ordine:
                    
                    try:
                        
                        Ordine[str(Colori[y]) + 'Up'].append(pixel)
                        
                    except:
                    
                        Ordine[str(Colori[y]) + 'Up'] = []    
                    
                        Ordine[str(Colori[y]) + 'Up'].append(pixel)
                    
                elif p1[w] != Colori[y] and p2[w] != Colori[y] and p1[w] == p2[w] and p1[w] != (0,0,0):    
                    
                    try:
                        
                        Ordine[str(Colori[y]) + 'Down'].append(p1[w])
                        
                    except:
                    
                        Ordine[str(Colori[y]) + 'Down'] = [] 
                    
                        Ordine[str(Colori[y]) + 'Down'].append(p1[w])
                    
                w += 1
                
            y += 1
                    
            x += 1

    for boh in range(2):
  
        x = 0
        
        y = 0
        
        z = 0
    
        temp = 0    
    
        # Inizio --> Fine y
    
        for Punti in list(Inizio.values()):
            
            if boh%2 == 1:

                temp = wid[x]
                
                p1 = Immagine[Punti[1]:Punti[1] + h[z]]
                
                a = 1
                
            else:
            
                p1 = Immagine[Punti[1]:Punti[1] + h[z]]
                
                a = -1
            
            z += 1
            
            w = 0
            
            for pixel in Immagine[Punti[1]:Punti[1] + h[x]]:
                
                if pixel[Punti[0] + temp] != Colori[y] and Colori[y] not in Ordine:
                    
                    try:
                        
                        Ordine[str(Colori[y]) + 'Up'].append(pixel[Punti[0] + temp])
                        
                    except:
                    
                        Ordine[str(Colori[y]) + 'Up'] = []    
                    
                        Ordine[str(Colori[y]) + 'Up'].append(pixel[Punti[0] + temp])
                    
                elif p1[w][Punti[0] + temp + a] != Colori[y] and p1[w][Punti[0] + temp - a] != Colori[y] and p1[w][Punti[0] + temp + a] != (0,0,0):    
                    
                    try:
                        
                        Ordine[str(Colori[y]) + 'Down'].append(p1[w][Punti[0] + temp + a])
                        
                    except:
                    
                        Ordine[str(Colori[y]) + 'Down'] = [] 
                    
                        Ordine[str(Colori[y]) + 'Down'].append(p1[w][Punti[0] + temp + a])
                    
                w += 1
                
            y += 1
                    
            x += 1              

    Ord2 = {}
    
    for key,value in Ordine.items():
        
        Ord2[key] = set(value)
        
    for colore in Colori:
        
        if str(colore)+'Up' in Ord2 and str(colore)+'Down' not in Ord2:
            
            ultimo = colore
            
        elif str(colore)+'Up' not in Ord2 and str(colore)+'Down' in Ord2:
            
            primo = colore
        
    Ordine_giusto = []

    Ordine_giusto.append(ultimo)
    
    Ordine_giusto.append(primo)
    
    i = -1
    
    Ord2.pop(str(ultimo)+'Up')
    
    Colori.remove(ultimo)

    while len(Ordine_giusto) < len(Colori) + 1:
        
        temp = Ordine_giusto[i]
        
        cont = {}
        
        if Ordine_giusto[0] == temp:
        
            break
        
        for el in Ord2[str(temp)+'Down']:
            
                cont[el] = 0
        
        for el in Ord2[str(temp)+'Down']:
            
            if el == ultimo:
                
                continue
            
            if el in Ordine_giusto:
                
                cont[el] = -10000000000
                
                continue
            
            for el1 in Ord2[str(temp)+'Down']:
            
                if el == el1 or el1 == ultimo:
                
                    continue
            
                else:
                
                    if el1 in Ord2[str(el)+'Down']:
                        
                        cont[el1] -= 1

        rip = False
        
        temp = set()

        if len(cont) > 1:

            for val in [*cont.values()]:
                
                if [*cont.values()].count(val) > 1:
                    
                    rip = True
                    
                    temp.add(val)
                    
            if rip and max([*cont.values()]) in temp:
                
                    col = []
                
                    for el in temp:
                        
                        if el == max(temp):
                        
                            for x in [*cont.keys()]:
                            
                                if cont[x] == el:
                                
                                    col.append(x)
                
                    if len(col) == 2 and primo in col:
                        
                        col.remove(primo)
                        
                        max_key = col[0]
                        
                    else:
                
                        max_key = Ripetizione(col,Ord2,ultimo)
                    
            else:

                max_key = max(cont, key=lambda k: cont[k])
        
        elif len([*cont.keys()]) == 1:
        
            max_key = el
        
        else:
            
            max_key = el1

        Ordine_giusto.insert(i,max_key)

        i -= 1

    return Ordine_giusto

def Ripetizione(colori,Ord2, ultimo):
    
    if len(colori) == 2 and ultimo in colori:
        
        colori.remove(ultimo)
        
        return colori[0]
    
    elif ultimo in colori:
        
        colori.remove(ultimo)
    
    cont = {}
    
    for colore in colori:
    
        cont[colore] = 0
    
    for ind in range(1,len(colori)):
        
        if colori[ind] == ultimo:
            
            continue
        
        if colori[ind] in Ord2[str(colori[ind-1])+'Down']:
            
            cont[colori[ind]] -= 1
            
        elif colori[ind-1] in Ord2[str(colori[ind])+'Down']:
            
            cont[colori[ind-1]] -= 1

    if [*cont.values()].count([*cont.values()][0]) == len([*cont.values()]):
        
         temp = []
        
         for colore in colori:
             
             for col in Ord2[str(colore)+'Down']:
             
                 temp.append(col)
        
         temp = Ripetizione(temp,Ord2, ultimo)
         
         for colore in colori :
             
             if temp in Ord2[str(colore)+'Down']:
                 
                 cont[colore] -= 1     

    return max(cont, key=lambda k: cont[k])

if __name__ == '__main__':
    pass
    # place your personal tests here
    
