#ifndef C_CODE_H_INCLUDED
#define C_CODE_H_INCLUDED

#include <stdio.h>
#include <unistd.h>
#include <sys/sysinfo.h>
#include <sys/utsname.h>
#include <stdlib.h>
#include <string.h>

#define BUFSIZE 1000

int main()
{
  char line[BUFSIZE];
  char ostype[BUFSIZE], hostname[BUFSIZE], osrelease[BUFSIZE], version[BUFSIZE];
  FILE* os_file = fopen("/proc/sys/kernel/ostype", "r");
  fgets(line, BUFSIZE, os_file);
  strcpy(ostype, line);
  fclose(os_file);

  os_file = fopen("/proc/sys/kernel/hostname", "r");
  fgets(line, BUFSIZE, os_file);
  strcpy(hostname, line);
  fclose(os_file);

  os_file = fopen("/proc/sys/kernel/osrelease", "r");
  fgets(line, BUFSIZE, os_file);
  strcpy(osrelease, line);
  fclose(os_file);

  os_file = fopen("/proc/sys/kernel/version", "r");
  fgets(line, BUFSIZE, os_file);
  strcpy(version, line);
  fclose(os_file);

  printf("A: Questions about turing's OS:\n");
  printf("1. The type of turing's OS is %s.\n", ostype);
  printf("2. The hostname is %s.\n", hostname);
  printf("3. The osrelease is %s.\n", osrelease);
  printf("4. The version is %s.\n", version);
  printf("\n");

  // B. Questions about turing's processors:
  // Question 1
    int num_processors = sysconf(_SC_NPROCESSORS_ONLN);
    printf("1. Turing has %d processors.\n", num_processors);

    // Question 2
    int num_cores = sysconf(_SC_NPROCESSORS_CONF);
    printf("2. Turing has %d physical multi-core chips.\n", num_cores / num_processors);

    // Question 3
    long uptime_in_seconds = sysconf(_SC_CPUTIME);
    printf("3. Turing has been up for %ld seconds.\n", uptime_in_seconds);

    // Question 4
    int uptime_in_days = uptime_in_seconds / (24 * 3600);
    int remaining_seconds = uptime_in_seconds % (24 * 3600);
    int uptime_in_hours = remaining_seconds / 3600;
    remaining_seconds = remaining_seconds % 3600;
    int uptime_in_minutes = remaining_seconds / 60;
    remaining_seconds = remaining_seconds % 60;

    printf("4. %d days, %d hours, %d minutes, %d seconds.\n", uptime_in_days, uptime_in_hours, uptime_in_minutes, remaining_seconds);

    return 0;
}

