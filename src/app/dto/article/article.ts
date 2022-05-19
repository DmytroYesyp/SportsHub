export class Article {
  id: number;
  title: string;
  description: string;
  publicationDate?: Date;
  alternativeText: string;
  caption: string;
  image: string;
  leagueId: number;
  teamIds: number[] = [];
}
