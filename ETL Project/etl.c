/**I Benjamin Rhiner (be998992) afﬁrm that this program is entirely my own work and that I 
have neither developed my code together with any another person, nor copied any
code from any other person, nor permitted my code to be copied or otherwise
used by any other person, nor have I copied, modiﬁed, or otherwise used program
code that I have found in any external source, including but not limited to,
online sources. I acknowledge that any violation of the above terms will be
treated as academic dishonesty.*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "airPdata.h"

/**PrintData(struct airPdata**)
	Prints all relevant members of airPdata struct according to header
	formatting.
   Input: struct airPdata**
   Output: void
*/
static void PrintData(struct airPdata* airP) {
	printf("%-12s %-11s %-42s %-34s %-3s %-15s %-16s %c\n",
		airP->siteNumber, airP->LocID, airP->fieldName, airP->city,
		airP->state, airP->latitude, airP->longitude, airP->controlTower);
}

int main(int argc, char **argv) {

	//OPEN FILE
	char* fname = argv[1];
	FILE *file = fopen(fname, "r");

	//FILE ERROR HANDLING
	if (file == 0) {
		fprintf(stderr, "etl ERROR: File \"%s\" not found\n", fname);
		exit(-1);
	}

	//PRINT HEADER
	printf("%-12s %-11s %-42s %-34s %-3s %-15s %-16s Tower\n", "FAA Site", 
		"Short Name", "Airport Name", "City", "ST", "Latitude", "Longitude");
	printf("%-12s %-11s %-42s %-34s %-3s %-15s %-16s =====\n", "========",
		"==========", "============", "====", "==", "========", "=========");

	//INITIALIZE STRUCT
	struct airPdata *airport = malloc(sizeof(airPdata));
	
	int nextChar = 0, count = 0, index = 0;
	char *tempArray = malloc(sizeof(char) * (50 + 1));

	while ((nextChar = fgetc(file)) != EOF) {

		if (',' == nextChar || '\n' == nextChar) {

			tempArray[index] = '\0';

			switch (count % 19) {
				case 0:
					airport->siteNumber = malloc(sizeof(char) * (index + 1));
					strcpy(airport->siteNumber, tempArray);
					break;
				case 1:
					airport->LocID = malloc(sizeof(char) * (index + 1));
					strcpy(airport->LocID, tempArray);
					break;
				case 2:
					airport->fieldName = malloc(sizeof(char) * (index + 1));
					strcpy(airport->fieldName, tempArray);
					break;
				case 3:
					airport->city = malloc(sizeof(char) * (index + 1));
					strcpy(airport->city, tempArray);
					break;
				case 4:
					airport->state = malloc(sizeof(char) * (index + 1));
					strcpy(airport->state, tempArray);
					break;
				case 8:
					airport->latitude = malloc(sizeof(char) * (index + 1));
					strcpy(airport->latitude, tempArray);
					break;
				case 9:
					airport->longitude = malloc(sizeof(char) * (index + 1));
					strcpy(airport->longitude, tempArray);
					break;
				case 14:
					airport->controlTower = tempArray[index - 1];
					PrintData(airport);
					free(airport->siteNumber);
					free(airport->LocID);
					free(airport->fieldName);
					free(airport->city);
					free(airport->state);
					free(airport->latitude);
					free(airport->longitude);
					break;
			}//end switch
			
			count++;
			index = 0;
		} else {
			tempArray[index] = nextChar;
			index++;
		}//end if...else
	}//end while

	//FREE MEMORY AND CLOSE FILE
	free(tempArray);
	free(airport);
	fclose(file);

	return 0;

}