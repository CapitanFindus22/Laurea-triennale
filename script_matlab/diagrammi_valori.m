%{
    Small script to visualize the values calculated by the 
    Program

%}

%Path of this script
this = matlab.desktop.editor.getActiveFilename;

%Path of the files
filePath = replaceBetween(this,"Anomaly_detection",length(this),"/test_gen/csv/");

%CSV files in the folder
files = dir(strcat(filePath,"*.csv"));

%Number of files
num_files = size(files);

disp("File disponibili:");

%List the files
for y=1:num_files(1)
    disp(strcat(int2str(y),') ',files(y,:).('name'),newline));
end

%Let the user choose the file
choice = -1;
while (isempty(choice) ...
        || ~isnumeric(choice) ...
        || ~isreal(choice) ...
        || ~isfinite(choice) ...
        || (choice ~= fix(choice)) ...
        || choice < 0 ...
        || choice > num_files(1) ...
        )

    choice = input("Scegli il file:");
end

%Open the file
tab = readtable(strcat(filePath,files(choice,:).('name')),VariableNamingRule="preserve");

disp(tab);

names = tab.Properties.VariableNames;

tab = tab{:,:};

rows = size(tab,1);
cols = size(tab,2);

%Let the user choose the window size
window = -1;
while (isempty(window) ...
        || ~isnumeric(window) ...
        || ~isreal(window) ...
        || ~isfinite(window) ...
        || (window ~= fix(window)) ...
        || window < 0 ...
        || window > rows ...
        )

    window = input("Scegli la dimensione della finestra:");
end

%Matrix containing the mean values
m = zeros(rows-window,cols);

%Matrix containing the covariance matrices
covar = zeros((rows-window)*cols,cols);

%Calculate the values
for tmp = 1:rows-window+1

    windows = tab(tmp:window+tmp-1,:);

    m(tmp,:) = mean(windows);

    covar(cols*(tmp-1)+1:cols*tmp,:) = cov(windows);

end

%Display the values
disp("Medie delle finestre:");
disp(m);

disp("Matrici delle covarianze per finestra:");
disp(covar);

disp("Andamento delle covarianze tra stream:");

%Plot the covariances
for x = 1:cols-1
    tmp = 1;
    cov_names = strings(1,cols-x);
    figure;
    for y = 2:cols
        if(y>x)
            plot(covar(x:cols:end,y));
            grid;
            hold on
            cov_names(tmp) = strcat(int2str(x-1),"-",int2str(y-1));
            tmp = tmp + 1;
        end
    end
    set(gcf, 'unit', 'inches');
    figure_size =  get(gcf, 'position');
    h_legend = legend(cov_names);
    set(h_legend, 'location', 'northeastoutside');
    set(h_legend, 'unit', 'inches');
    legend_size = get(h_legend, 'position');
    figure_size(3) = figure_size(3) + legend_size(3);
    set(gcf, 'position', figure_size);
    xlabel("Numero di finestre");
    ylabel("Valore");
    hold off
end

%Plot the mean
figure;
plot(m);
grid;
title("Andamento delle medie");
set(gcf, 'unit', 'inches');
figure_size =  get(gcf, 'position');
h_legend = legend(names);
set(h_legend, 'location', 'northeastoutside');
set(h_legend, 'unit', 'inches');
legend_size = get(h_legend, 'position');
figure_size(3) = figure_size(3) + legend_size(3);
set(gcf, 'position', figure_size);
xlabel("Numero di finestre");
ylabel("Valore");

clear;