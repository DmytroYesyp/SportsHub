export class Article {
  id: number;
  title: string;
  description: string;
  text: string;
  publicationDate?: Date;
  alternativeText: string;
  caption: string;
  image: string;
  isPublished: boolean;
  mainPageOrder: number | null;
  leagueId: number;
  teamIds: number[] = [];
}
