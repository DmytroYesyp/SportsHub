import {League} from "../../league/league";
import {SportKindContent} from "./sport-kind-content";

export class SportKind extends SportKindContent {
  id: number;
  leagues: League[];
}
