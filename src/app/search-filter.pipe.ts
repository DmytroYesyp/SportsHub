import { Pipe, PipeTransform } from '@angular/core';
import {videoEntity} from "./admin-video/admin-video.component";


@Pipe({ name: 'appFilter' })
export class SearchFilterPipe implements PipeTransform {



  transform(items: any, searchText: string): any[] {
    if (!items) {
      return [];
    }
    if (!searchText) {
      return items;
    }
    searchText = searchText.toLocaleLowerCase();

    return items.filter(it => {

      if (!!it.description)
        return it.description.toString().toLocaleLowerCase().includes(searchText);
      if (!!it.name)
        return it.name.toString().toLocaleLowerCase().includes(searchText);

    });
  }
}
