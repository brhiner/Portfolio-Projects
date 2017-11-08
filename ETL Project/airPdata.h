typedef struct airPdata {
	char* siteNumber; //FAA Site Number
	char* LocID; //Airport's 'short name', /textit[e.g.] MCO
	char* fieldName; //Airport Name
	char* city; //Associated City
	char* state; //State
	char* latitude; //Latitude
	char* longitude; //Longitude
	char controlTower; //Control Tower, this is a single character (Y/N)
} airPdata;
