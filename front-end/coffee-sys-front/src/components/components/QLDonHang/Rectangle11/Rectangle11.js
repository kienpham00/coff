import React from 'react'

import resets from '../../_resets.module.css';
import classes from './Rectangle11.module.css';


/* @figmaId 9:677 */
const Rectangle11 = (props) => {
  return (
    <div className={`${resets.storybrainResets} ${classes.root}`}>
      <div className={classes.rectangle11}></div>
      {props.text?.text != null ? props.text?.text : <div className={classes.text}>Text</div>}
    </div>
  );
};

export default Rectangle11
