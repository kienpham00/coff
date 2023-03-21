import React from 'react'

import resets from '../../_resets.module.css';
import ItemButton1 from '../ItemButton1/ItemButton1';
import ItemText from '../ItemText/ItemText';
import classes from './Row.module.css';

/* @figmaId 44:154 */
const Row = (props) => {
  return (
    <div className={`${resets.storybrainResets} ${props.classes?.root || ''} ${props.className || ''} ${classes.root}`}>
      <ItemText
        className={`${props.classes?.itemText || ''} ${classes.itemText}`}
        text={{
          tEXT: props.text?.tEXT,
        }}
      />
      <ItemText
        className={classes.itemText2}
        text={{
          tEXT: props.text?.tEXT2,
        }}
      />
      <ItemText
        className={classes.itemText3}
        text={{
          tEXT: props.text?.tEXT3,
        }}
      />
      <ItemText
        className={classes.itemText4}
        text={{
          tEXT: props.text?.tEXT4,
        }}
      />
      {props.swap?.itemButton1 || <ItemButton1 className={classes.itemButton1} />}
    </div>
  );
};

export default Row