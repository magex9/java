#!/usr/bin/perl

my @lines = `docker images`;
my $first = shift @lines;
$first =~ s/([A-Z]) ([A-Z])/\1_\2/g;

my @headers = ($first =~ /([A-Z_]+ +)/g);
my $offset = (length $headers[0]) + (length $headers[1]);

foreach my $line (@lines) {
	my $image = substr($line, $offset, 12);
	system("docker rmi $image");
	print "Removed $image\n";
}
