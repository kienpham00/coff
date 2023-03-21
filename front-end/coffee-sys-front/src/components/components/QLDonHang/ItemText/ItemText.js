import React from 'react'
import resets from '../../_resets.module.css';
import classes from './ItemText.module.css';

/* @figmaId 8:294 */
const ItemText = (props) => {
  return (
    <div className={`${resets.storybrainResets} ${props.classes?.root || ''} ${props.className || ''} ${classes.root}`}>
      {props.text?.tEXT != null ? props.text?.tEXT : <div className={classes.tEXT}>TEXT</div>}
    </div>
  );
};

export default ItemText
