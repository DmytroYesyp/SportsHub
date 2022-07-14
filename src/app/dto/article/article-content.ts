export class ArticleContent {
  title: string;
  description: string;
  publicationDate?: Date;
  alternativeText: string;
  caption: string;
  image: string;
  isPublished: boolean;
  leagueId: number;
  teamIds: number[] = [];
}
