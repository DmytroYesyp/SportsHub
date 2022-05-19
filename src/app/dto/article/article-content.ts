export class ArticleContent {
  title: string;
  description: string;
  publicationDate?: Date;
  alternativeText: string;
  caption: string;
  image: string;
  leagueId: number;
  teamIds: number[] = [];
}
