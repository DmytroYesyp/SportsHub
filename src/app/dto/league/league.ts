import {Team} from "../team/team";

export class League {
  id: number;
  name: string;
  teams: Team[] = [];
}
