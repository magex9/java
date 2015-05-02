#!/usr/bin/perl

my @lines = `docker ps`;

foreach my $line (@lines) {
	my $container = substr($line, 0, 12);
	system("docker stop $container");
	print("Removed $container");
}
